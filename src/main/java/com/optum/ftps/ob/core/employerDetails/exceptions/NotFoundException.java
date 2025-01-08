package com.optum.ftps.ob.core.employerDetails.exceptions;

import com.optum.ftps.ob.core.employerDetails.exceptions.model.ErrorItem;

import java.util.List;

public class NotFoundException extends EmployerDetailsException {

    public NotFoundException(List<ErrorItem> errors) {
        super(errors);
    }
}
