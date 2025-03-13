package com.azki.insurance.presentation.common.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com.azki.insurance.logging")
public record LoggingConfigData(List<String> nonLoggedPaths,List<String> ignoredHeaders) {
}
