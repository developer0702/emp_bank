package com.optum.ftps.ob.core.contribution.exceptions;

import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;

import java.util.List;

public class NotFoundException extends ContributionException {

    public NotFoundException(List<ErrorItem> errors) {
        super(errors);
    }
}
