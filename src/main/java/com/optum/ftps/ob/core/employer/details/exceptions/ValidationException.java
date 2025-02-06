package com.optum.ftps.ob.core.employer.details.exceptions;

import java.util.List;

public class ValidationException extends Exception {
    private final List<Integer> errorCodes;

    public ValidationException() {
        this.errorCodes = null;
    }

    public ValidationException(String message) {
        super(message);
        this.errorCodes = null;
    }

    public ValidationException(String message, List<Integer> errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public ValidationException(List<Integer> errorCodes) {
        super("Validation error occurred");
        this.errorCodes = errorCodes;
    }

    public List<Integer> getErrorCodes() {
        return errorCodes;
    }
}
