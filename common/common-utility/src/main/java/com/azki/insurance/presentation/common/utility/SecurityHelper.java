package com.azki.insurance.presentation.common.utility;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public final class SecurityHelper {

    private static final String SHA1_DIGEST_NAME = "SHA-1";
    private static final String PASSWORD_FORMAT = "%s|%s";
    private static final String SHA256_DIGEST_NAME = "SHA-256";
    private static final String SHA512_DIGEST_NAME = "SHA-512";
    private static final int SHA1_LENGTH = 20;
    private static final int SHA256_LENGTH = 32;
    private static final int SHA512_LENGTH = 64;

    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();
    private static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();
    private static final SecureRandom PRNG = new SecureRandom();

    public static String generateUserPassword(String userName, String plainPassword) {
        return generateUserPassword(userName, plainPassword, PasswordHashTypeEnum.Sha256);
    }

    public static String generateUserPassword(String userName, String plainPassword, PasswordHashTypeEnum hashType) {
        if (hashType.isShaBased()) {
            String basePassword = String.format(PASSWORD_FORMAT, userName, plainPassword);
            return generateShaBasedPassword(basePassword, hashType, 0);
        } else {
            throw new IllegalArgumentException("Bad password hash is provided.");
        }
    }

    public static String generateShaBasedPassword(String plainPassword, PasswordHashTypeEnum hashType, int saltLength) {
        if (!hashType.isShaBased()) {
            throw new IllegalArgumentException("Non SHA-Based algorithm");
        }

        String digestName = switch (hashType) {
            case Sha1, Sha1WithSalt -> SHA1_DIGEST_NAME;
            case Sha256, Sha256WithSalt -> SHA256_DIGEST_NAME;
            case Sha512, Sha512WithSalt -> SHA512_DIGEST_NAME;
            default -> throw new IllegalArgumentException("Bad SHA-Based algorithm");
        };

        if ((!hashType.isSaltBased() && saltLength > 0) || (hashType.isSaltBased() && saltLength <= 0)) {
            throw new IllegalArgumentException("Bad salt length");
        }

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(digestName);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] effectivePlainPasswordBytes;
        byte[] plainPasswordBytes = plainPassword.getBytes(StandardCharsets.UTF_8);

        if (hashType.isSaltBased()) {
            byte[] saltBytes = new byte[saltLength];
            PRNG.nextBytes(saltBytes);

            effectivePlainPasswordBytes = Arrays.copyOf(saltBytes, saltLength + plainPasswordBytes.length);
            System.arraycopy(plainPasswordBytes, 0, effectivePlainPasswordBytes, saltLength, plainPasswordBytes.length);

            digest.update(saltBytes);
        } else {
            effectivePlainPasswordBytes = plainPasswordBytes;
        }

        byte[] generatedHashBytes = digest.digest(effectivePlainPasswordBytes);

        return String.format("%02d", hashType.getHashTypeCode()) + encodeBase64(generatedHashBytes);
    }

    private static String encodeBase64(byte[] bytes) {
        return BASE64_ENCODER.encodeToString(bytes);
    }

    public static boolean validateUserPassword(String userName, String plainPassword, String passwordHash) {
        int hashTypeCode = Integer.parseInt(passwordHash.substring(0, 2));
        PasswordHashTypeEnum passwordHashType = PasswordHashTypeEnum.getByHashTypeCode(hashTypeCode);

        if (passwordHashType.isShaBased()) {
            String basePassword = String.format(PASSWORD_FORMAT, userName, plainPassword);
            return validateShaBasedPassword(basePassword, passwordHash.substring(2), passwordHashType);
        } else {
            throw new IllegalArgumentException("Bad password hash is provided.");
        }
    }

    public static boolean validateShaBasedPassword(String plainPassword, String passwordHash, PasswordHashTypeEnum hashType) {
        if (!hashType.isShaBased()) {
            throw new IllegalArgumentException("Non SHA-Based algorithm");
        }

        byte[] storedBytes = BASE64_DECODER.decode(passwordHash);

        int hashLength;
        int saltLength;
        String digestName;

        int storedLength = storedBytes.length;

        switch (hashType) {
            case Sha1:
            case Sha1WithSalt:
                hashLength = SHA1_LENGTH;
                digestName = SHA1_DIGEST_NAME;
                break;
            case Sha256:
            case Sha256WithSalt:
                hashLength = SHA256_LENGTH;
                digestName = SHA256_DIGEST_NAME;
                break;
            case Sha512:
            case Sha512WithSalt:
                hashLength = SHA512_LENGTH;
                digestName = SHA512_DIGEST_NAME;
                break;
            default:
                return false;
        }

        saltLength = storedLength - hashLength;

        if (!hashType.isSaltBased() && saltLength > 0) {
            return false;
        }

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(digestName);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] effectivePlainPasswordBytes;
        byte[] plainPasswordBytes = plainPassword.getBytes(StandardCharsets.UTF_8);

        if (hashType.isSaltBased()) {
            byte[] saltBytes = Arrays.copyOfRange(storedBytes, 0, saltLength);

            effectivePlainPasswordBytes = Arrays.copyOf(saltBytes, saltLength + plainPasswordBytes.length);
            System.arraycopy(plainPasswordBytes, 0, effectivePlainPasswordBytes, saltLength, plainPasswordBytes.length);

            digest.update(saltBytes);
        } else {
            effectivePlainPasswordBytes = plainPasswordBytes;
        }

        byte[] generatedHashBytes = digest.digest(effectivePlainPasswordBytes);
        byte[] passwordHashBytes = Arrays.copyOfRange(storedBytes, saltLength, storedLength);

        return Arrays.equals(generatedHashBytes, passwordHashBytes);
    }

    @Getter
    public enum PasswordHashTypeEnum {

        BCrypt(false, true, 0),
        Sha1(true, false, 1),
        Sha1WithSalt(true, true, 2),
        Sha256(true, false, 3),
        Sha256WithSalt(true, true, 4),
        Sha512(true, false, 5),
        Sha512WithSalt(true, true, 6);

        private static final HashMap<Integer, PasswordHashTypeEnum> VALUE_BY_HASH_TYPE_CODE_MAP = new HashMap<>();

        static {
            for (PasswordHashTypeEnum enumValue : PasswordHashTypeEnum.values()) {
                VALUE_BY_HASH_TYPE_CODE_MAP.put(enumValue.getHashTypeCode(), enumValue);
            }
        }

        @JsonValue
        public final int hashTypeCode;
        public final boolean shaBased;
        public final boolean saltBased;

        PasswordHashTypeEnum(boolean shaBased, boolean saltBased, int hashTypeCode) {
            this.shaBased = shaBased;
            this.saltBased = saltBased;
            this.hashTypeCode = hashTypeCode;
        }

        public static PasswordHashTypeEnum getByHashTypeCode(int hashTypeCode) {
            return VALUE_BY_HASH_TYPE_CODE_MAP.get(hashTypeCode);
        }

    }
}
