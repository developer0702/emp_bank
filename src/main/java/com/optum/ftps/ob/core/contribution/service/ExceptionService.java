package com.optum.ftps.ob.core.contribution.service;

import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;

import java.util.Collection;

public interface ExceptionService {

    ErrorItem getError(Integer errorCode);

    void handleValidationError(Collection<Integer> codes);
}
