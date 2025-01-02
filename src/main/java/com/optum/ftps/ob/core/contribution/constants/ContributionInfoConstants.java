package com.optum.ftps.ob.core.contribution.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContributionInfoConstants {

    public static final int REQUEST_ID_LENGTH = 36;
    public static final int ACH_FILEID_LENGTH = 10;
    public static final int CONTRB_EMPLOYER_GROUPID_LENGTH = 9;
    public static final int CONTRB_RESTRICT_TO_FUND_INDICATOR_LENGTH = 1;
    public static final int CONTRB_MAX_NUMBER_OF_FILES_LENGTH = 2;

    public static final int REQUEST_SUCCESSFULLY_PROCESSED = 9;
    public static final int RECORD_NOT_FOUND_ERROR_CODE = 32;
    public static final int REQUEST_LIMITED_CODE = 37;

    public static final String BIS_SCHEMA_NAME = "BIS";

    public static final String CONTRIBUTION_SERVICE_SCHEMA_NAME = "contribution_service";

    public static final String FTPS_SCHEMA_NAME = "dbo";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String EMPTY_STRING = "";
}
