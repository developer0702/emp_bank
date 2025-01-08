package com.optum.ftps.ob.core.employerDetails.exceptions.model;

/**
 * This enum will contain all error codes of the application. Key -> error code Description ->
 * description of the error
 */
public enum ErrorCodes {
    /** Error: It was not possible to find any entity with the given criteria */
    NOT_FOUND("Unable to find a {0} for the specified criteria"),
    /** Error: Internal server error (any unexpected error) */
    INTERNAL_SERVER_ERROR("Internal server error"),
    /** Error: Generic error for invalid parameter scenarios */
    INVALID_PARAMETER("Missing or invalid {0} parameter"),
    /** Error: Generic error for unsupported operations */
    FORBIDDEN_OPERATION("This operation is not allowed"),
    /** Error: Generic error for missing header. */
    MISSING_HEADER("Missing required {0} header."),
    /** Error: Generic error for invalid header scenarios */
    INVALID_HEADER("Invalid value for {0} header."),
    /** Error: The operation is not authorized. */
    UNAUTHORIZED("The operation is not authorized."),
    /** Error: Entity already exists */
    ALREADY_EXISTS("The {0} already exist in the system");

    /** Description of the error */
    public final String description;

    ErrorCodes(String description) {
        this.description = description;
    }
}
