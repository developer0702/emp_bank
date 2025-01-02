package com.optum.ftps.ob.core.contribution.exceptions;

import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;

import java.util.List;

public class ServiceException extends ContributionException {

    public ServiceException(List<ErrorItem> errors) {
        super(errors);
    }
}
