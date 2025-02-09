package com.optum.ftps.ob.core.employer.details.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final List<ErrorItem> errors = new ArrayList<>();

    public ErrorResponse addItem(ErrorItem errorsItem) {
        this.errors.add(errorsItem);
        return this;
    }

    @JsonProperty("errors")
    public List<ErrorItem> getErrors() {
        return errors;
    }
}
