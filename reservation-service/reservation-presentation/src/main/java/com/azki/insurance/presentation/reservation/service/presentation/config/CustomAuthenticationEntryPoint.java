package com.azki.insurance.presentation.reservation.service.presentation.config;

import com.azki.insurance.presentation.api.data.BaseEdgeResponseDTO;
import com.azki.insurance.presentation.common.core.data.ErrorCodeEnum;
import com.azki.insurance.presentation.common.core.data.ErrorDTO;
import com.azki.insurance.presentation.common.utility.JsonSerializationUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        BaseEdgeResponseDTO edgeResponse = new BaseEdgeResponseDTO();
        ErrorDTO error = new ErrorDTO(ErrorCodeEnum.SECURITY_ERROR, "Unauthorized");
        edgeResponse.addError(error);
        String jsonResponse = JsonSerializationUtil.objectToJsonString(edgeResponse);

        response.getWriter().write(Objects.requireNonNull(jsonResponse));
    }
}
