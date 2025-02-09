package com.optum.ftps.ob.core.employer.details.exceptions;

import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorItem;

import java.util.List;

public class ServiceException extends EmployerDetailsException {

    public ServiceException(List<ErrorItem> errors) {
        super(errors);
    }
}
