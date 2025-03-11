package com.azki.insurance.reservation.service.application.api.data.security;

import com.azki.insurance.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateUserEdgeRequestDTO extends BaseEdgeRequestDTO {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;
}