package com.azki.insurance.common.bootstrap;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "com.azki.insurance")
public class BootstrapConfiguration {
}
