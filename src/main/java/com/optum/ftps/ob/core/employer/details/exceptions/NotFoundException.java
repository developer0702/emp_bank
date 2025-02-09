package com.optum.ftps.ob.core.employer.details.exceptions;

import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorItem;

import java.util.List;

public class NotFoundException extends EmployerDetailsException {

    public NotFoundException(List<ErrorItem> errors) {
        super(errors);
    }
}
