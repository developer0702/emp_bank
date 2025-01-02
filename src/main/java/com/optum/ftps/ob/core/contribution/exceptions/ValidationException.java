package com.optum.ftps.ob.core.contribution.exceptions;

import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;

import java.util.List;

public class ValidationException extends ContributionException {

    public ValidationException(List<ErrorItem> errors) {
        super(errors);
    }
}
