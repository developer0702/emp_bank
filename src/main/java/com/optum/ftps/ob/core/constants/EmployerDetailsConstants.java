package com.optum.ftps.ob.core.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployerDetailsConstants {

        public static final int BNK_REQUESTID_LENGTH = 36;
        public static final int BNK_REQ_USERID_LENGTH = 30;
        public static final int BNK_SRC_SYS_ID_LENGTH = 10;
        public static final int EMP_GROUP_ID_LENGTH = 9;
        public static final int BNK_RTR_NO_LENGTH = 9;
        public static final int BNK_ACCT_NO_LENGTH = 17;
        public static final int BNK_NAME_LENGTH = 40;
        public static final String BNK_ACCT_TYPE_CD_S = "S";
        public static final String BNK_ACCT_TYPE_CD_C = "C";
        public static final String BNK_ACCT_STS_CD_A = "A";
        public static final String BNK_ACCT_STS_CD_I = "I";
        public static final String BNK_ACCT_OPERATION_CD_ADD = "ADD";
        public static final String BNK_ACCT_OPERATION_CD_UPDATE = "UPDATE";
        public static final String BNK_ACCNT_OPERATION_CD = "Invalid operation code";
        public static final String CUST_FUND_ID_ERROR = "Customer fund ID error";
        public static final int MAX_CONTRIBUTION_ACCOUNT_ALLOWED = 20;
    }
}
