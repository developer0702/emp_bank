package com.optum.ftps.ob.core.employerDetails.exceptions;

import com.optum.ftps.ob.core.employerDetails.exceptions.model.ErrorItem;

import java.util.List;

public class ServiceException extends EmployerDetailsException {

    public ServiceException(List<ErrorItem> errors) {
        super(errors);
    }
}
