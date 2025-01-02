package com.optum.ftps.ob.core.contribution.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodeConstants {

    public static final int REQUIRED_FIELDS_MISSING = 1;
    public static final int INCORRECT_FORMAT = 2;
    public static final int RECORD_NOT_FOUND_ERROR_CODE = 32;
    public static final int REQUEST_SUCCESSFULLY_PROCESSED = 9;
    public static final int SERVICE_FAILED_ERROR_CODE = 13;
}
