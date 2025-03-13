package com.azki.insurance.presentation.reservation.service.presentation.api.data.security;

import com.azki.insurance.presentation.api.data.BaseEdgeRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthenticationEdgeRequestDTO extends BaseEdgeRequestDTO {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;
}
