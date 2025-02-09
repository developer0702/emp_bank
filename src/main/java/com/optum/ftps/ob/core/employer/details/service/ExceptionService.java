package com.optum.ftps.ob.core.employer.details.service;

import com.optum.ftps.ob.core.employer.details.exceptions.ValidationException;
import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorItem;

import java.util.Collection;

public interface ExceptionService {

    ErrorItem getError(Integer errorCode);

    void handleValidationError(Collection<Integer> codes) throws ValidationException;
}
