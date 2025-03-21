package com.azki.insurance.common.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com.azki.insurance")
public record CommonConfigData(Integer nodeId,
                               List<String> ignoredPathPatterns,
                               String serviceName,
                               ApiDoc apiDoc,
                               String adminRoleName,
                               String userRoleName,
                               Long adminUserId) {

    public record ApiDoc(String pathPrefix) {
    }

}
