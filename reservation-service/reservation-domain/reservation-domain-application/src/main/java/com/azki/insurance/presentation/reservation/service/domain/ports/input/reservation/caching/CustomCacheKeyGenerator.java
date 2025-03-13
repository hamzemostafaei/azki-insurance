package com.azki.insurance.presentation.reservation.service.domain.ports.input.reservation.caching;

import com.azki.insurance.presentation.common.utility.ReflectionUtil;
import jakarta.annotation.Nonnull;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Component("customKeyGenerator")
public class CustomCacheKeyGenerator implements KeyGenerator {

    @Nonnull
    @Override
    public Object generate(@Nonnull Object target, @Nonnull Method method, Object... params) {
        StringJoiner key = new StringJoiner("_");

        for (Object param : params) {
            if (param != null) {
                key.add(extractFields(param));
            }
        }

        return key.toString();
    }

    private String extractFields(Object obj) {

        StringJoiner key = new StringJoiner("|", "[", "]");

        List<Field> fields = ReflectionUtil.getFields(obj.getClass());
        fields.stream()
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(obj);
                        if (value instanceof Date date) {
                            key.add(field.getName() + "=" + date.getTime());
                        } else {
                            key.add(field.getName() + "=" + (value != null ? value.toString() : "null"));
                        }
                    } catch (IllegalAccessException e) {
                        key.add(field.getName() + "=ERROR");
                    }
                });

        return key.toString();
    }
}
