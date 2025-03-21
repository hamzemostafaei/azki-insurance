package com.azki.insurance.presentation.api.data;

import com.azki.insurance.common.core.data.ErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEdgeResponseDTO extends BaseEdgeRequestDTO {

    public BaseEdgeResponseDTO() {
        this.status = ServiceStatusEnum.SUCCESSFUL;
    }

    @JsonProperty("status")
    private ServiceStatusEnum status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<ErrorDTO> errors;

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;

        if (!CollectionUtils.isEmpty(errors)) {
            setStatus(ServiceStatusEnum.UNSUCCESSFUL);
        }
    }

    public void addError(ErrorDTO error) {
        if (getErrors() == null) {
            setErrors(new ArrayList<>(Collections.singletonList(error)));
        } else {
            getErrors().add(error);
        }

        setStatus(ServiceStatusEnum.UNSUCCESSFUL);
    }

    public boolean hasError() {
        List<ErrorDTO> errors = getErrors();
        return errors != null && !errors.isEmpty();
    }
}
