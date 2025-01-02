package com.optum.ftps.ob.core.contribution.exceptions;

import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ContributionException extends RuntimeException {

    private final List<ErrorItem> errors = new ArrayList<>();

    public ContributionException(List<ErrorItem> errors) {
        this.errors.addAll(errors);
    }
}
