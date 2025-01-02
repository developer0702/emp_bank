package com.optum.ftps.ob.core.contribution.constants;

import lombok.Getter;

@Getter
public enum FileDetailsRequestType {
    VALID_REQ("V"),
    INVALID_REQ("I"),
    ALL_REQ("A");

    private final String requestType;

    FileDetailsRequestType(String requestType) {
        this.requestType = requestType;
    }
}
