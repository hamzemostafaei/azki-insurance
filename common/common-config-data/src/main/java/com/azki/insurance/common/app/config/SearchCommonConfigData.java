package com.azki.insurance.common.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.azki.insurance.search")
public record SearchCommonConfigData(Integer pageSize) {
}
