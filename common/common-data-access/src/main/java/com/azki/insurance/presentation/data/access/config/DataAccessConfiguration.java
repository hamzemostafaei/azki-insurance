package com.azki.insurance.presentation.data.access.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.azki.insurance")
@EnableJpaRepositories(basePackages = "com.azki.insurance")
public class DataAccessConfiguration {
}
