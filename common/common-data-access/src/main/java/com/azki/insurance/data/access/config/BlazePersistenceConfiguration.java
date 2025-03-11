package com.azki.insurance.data.access.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.spi.EntityManagerFactoryIntegrator;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BlazePersistenceConfiguration {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public CriteriaBuilderConfiguration criteriaBuilderConfig() {
        return Criteria.getDefault();
    }

    @Bean
    public CriteriaBuilderFactory createCriteriaBuilderFactory(CriteriaBuilderConfiguration config) {
        return config.createCriteriaBuilderFactory(entityManagerFactory);
    }

    @Bean
    public EntityManagerFactoryIntegrator getEntityManagerIntegrators(CriteriaBuilderConfiguration config) {
        return config.getEntityManagerIntegrators().getFirst();
    }
}
