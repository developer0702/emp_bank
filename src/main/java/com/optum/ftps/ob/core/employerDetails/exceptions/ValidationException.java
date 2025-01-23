package com.optum.ftps.ob.core.employerDetails.exceptions;

import java.util.List;

public class ValidationException extends Exception {
    private final List<Integer> errorCodes;

    /**
     * Constructor
     */
    public ValidationException() {
        // Default constructor
        this.errorCodes = null;
    }

    /**
     * Constructor
     * @param message java.lang.String
     */
    public ValidationException(String message) {
        super(message);
        this.errorCodes = null;
    }

    /**
     * Constructor with the error codes and message
     * @param message String
     * @param errorCodes List<Integer>
     */
    public ValidationException(String message, List<Integer> errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    /**
     * Constructor
     * @param errorCodes List<Integer>
     */
    public ValidationException(List<Integer> errorCodes) {
        super("Validation error occurred");
        this.errorCodes = errorCodes;
    }

    public List<Integer> getErrorCodes() {
        return errorCodes;
    }
}
