package com.azki.insurance.reservation.service.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Reservation service API",
                version = "1.0",
                description = "API documentation for reservation service",
                contact = @Contact(name = "Hamze Mostafaei", email = "hamze.mostafaei@gmail.com")
        )
)
public class ReservationServiceAPIDocConfig {
}
