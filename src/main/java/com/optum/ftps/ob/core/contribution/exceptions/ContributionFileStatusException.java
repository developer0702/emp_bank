package com.optum.ftps.ob.core.contribution.exceptions;

import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;

import lombok.Getter;

import java.util.List;

@Getter
public class ContributionFileStatusException extends ContributionException {

    public ContributionFileStatusException(List<ErrorItem> errors) {
        super(errors);
    }
}
