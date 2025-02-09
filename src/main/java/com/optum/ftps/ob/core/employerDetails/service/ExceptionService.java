package com.optum.ftps.ob.core.employerDetails.service;



import com.optum.ftps.ob.core.employerDetails.exceptions.ValidationException;
import com.optum.ftps.ob.core.employerDetails.exceptions.model.ErrorItem;

import java.util.Collection;

public interface ExceptionService {

    ErrorItem getError(Integer errorCode);

    void handleValidationError(Collection<Integer> codes) throws ValidationException;
}
