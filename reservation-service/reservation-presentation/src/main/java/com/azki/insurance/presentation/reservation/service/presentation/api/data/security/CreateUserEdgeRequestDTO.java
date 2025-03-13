package com.azki.insurance.presentation.reservation.service.presentation.api.data.security;

import com.azki.insurance.presentation.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateUserEdgeRequestDTO extends BaseEdgeRequestDTO {

    @NotEmpty
    @JsonProperty("userName")
    private String userName;

    @NotEmpty
    @JsonProperty("password")
    private String password;

    @Email
    @JsonProperty("email")
    private String email;
}