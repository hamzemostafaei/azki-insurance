package com.azki.insurance.reservation.service.application.api.data.security;

import com.azki.insurance.api.data.BaseEdgeResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserEdgeResponseDTO extends BaseEdgeResponseDTO {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

}