package com.optum.ftps.ob.core.contribution.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContributionFileConstants {

    public static final String RESPONSE_CODE_FIFTEEN = "0015";
    public static final String RESPONSE_CODE_FIFTEEN_VALUE =
            "CANNOT UPDATE STATUS SINCE PRIOR STATUS IS NOT EQUAL TO PENDING";
    public static final String RESPONSE_CODE_SIXTEEN = "0016";
    public static final String RESPONSE_CODE_SIXTEEN_VALUE =
            "FUND ID IS NOT VALID FOR THIS EMPLOYER GROUP";
    public static final String RESPONSE_SEVERITY_INF = "INF";
    public static final String STATUS_PENDING = "P";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
}
