package com.optum.ftps.ob.core.employerDetails.exceptions;

import com.optum.ftps.ob.core.employerDetails.exceptions.model.ErrorItem;

import java.util.List;

public class ValidationException extends EmployerDetailsException {

    public ValidationException(List<ErrorItem> errors) {
        super(errors);
    }
}
