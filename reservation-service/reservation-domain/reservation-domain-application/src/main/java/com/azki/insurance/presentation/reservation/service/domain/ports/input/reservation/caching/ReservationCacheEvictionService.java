package com.azki.insurance.presentation.reservation.service.domain.ports.input.reservation.caching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCacheEvictionService {

    private final RedisTemplate<Object, Object> redisTemplate;

    private final CacheProperties cacheProperties;

    public void evictAvailableSlotsCache() {

        @SuppressWarnings("DataFlowIssue")
        RedisKeyCommands redisKeyCommands = redisTemplate
                .getConnectionFactory()
                .getConnection()
                .keyCommands();

        try (Cursor<byte[]> scan =
                     redisKeyCommands.scan(ScanOptions.scanOptions().match(cacheProperties.getRedis().getKeyPrefix() + "available_slots::*").build())) {
            while (scan.hasNext()) {
                byte[] key = scan.next();
                if (log.isDebugEnabled()) {
                    log.debug("Deleting cache key: {}", new String(key));
                }
                redisTemplate.getConnectionFactory().getConnection().keyCommands().del(key);
            }
        }
    }
}
