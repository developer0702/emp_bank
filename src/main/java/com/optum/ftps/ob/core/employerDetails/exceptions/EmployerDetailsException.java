package com.optum.ftps.ob.core.employerDetails.exceptions;

import com.optum.ftps.ob.core.employerDetails.exceptions.model.ErrorItem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmployerDetailsException extends RuntimeException {

    private final List<ErrorItem> errors = new ArrayList<>();

    public EmployerDetailsException(List<ErrorItem> errors) {
        this.errors.addAll(errors);
    }
}
