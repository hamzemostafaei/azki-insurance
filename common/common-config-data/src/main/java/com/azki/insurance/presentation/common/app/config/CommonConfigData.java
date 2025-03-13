package com.azki.insurance.presentation.common.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com.azki.insurance")
public record CommonConfigData(Integer nodeId,
                               List<String> ignoredPathPatterns,
                               String serviceName) {
}
