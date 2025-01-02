package com.optum.ftps.ob.core.contribution.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContributionFileQueryConstants {
    /**
     * TODO: This query is using the table clogExternalACH as a temporal solution for getting the
     * External Bank information that in BIS is present in the CUST_FUND table. This needs to be
     * changed in the future once that information is in the FTPS DB. That table is missing bank
     * nick name, so we are using an empty string for now.
     */
    public static final String SQL_SELECT_HISTORY_FROM_CONTRIBUTION_PORTAL_FILE =
            """
    SELECT TOP 500\
        a.contribution_portal_file_id,\
        a.effective_transaction_date,\
        a.status_code,\
        a.submit_method_code,\
        a.total_file_contribution_amount,\
        a.original_file_name,\
        a.new_file_name,\
        a.friendly_file_name,\
        a.submit_by_name,\
        a.submit_timestamp,\
        a.approved_by_name,\
        a.approved_timestamp,\
        a.deny_by_name,\
        a.deny_timestamp,\
        a.deny_comment_text,\
        a.process_timestamp,\
        a.customer_policy_number,\
        a.approver_email_send_datetime,\
        a.customer_fund_id,\
        b.ExternalBankName,
        '' AS bankNickName
    FROM\
        contribution_service.contribution_portal_file a\
    LEFT OUTER JOIN dbo.clogExternalACH b\
        ON a.customer_policy_number = b.GroupID\
    WHERE\
        a.customer_policy_number = :customerPolicyNumber\
        AND a.effective_transaction_date >= :effectiveTransactionDate\
    ORDER BY\
        a.effective_transaction_date DESC
""";

    public static final String CONTRIBUTION_FILE_NAMES =
            "SELECT contribution_portal_file_id, effective_transaction_date, process_timestamp,"
                    + " original_file_name, new_file_name, friendly_file_name FROM"
                    + " contribution_service.contribution_portal_file ";
    public static final String CONTRIBUTION_FILE_CONDITION =
            " WHERE contribution_portal_file_id IN (";
    public static final String BRACKET = ")";

    public static final String SELECT_SQL_CONTRIBUTION_FILE_TRANSACTION_STATUS =
            "SELECT contribution_portal_file_id, account_number, transaction_description, ssn,"
                + " contribution_amount, contribution_type_name, portal_contribution_comment_text"
                + " FROM contribution_service.contribution_portal_file_detail WHERE"
                + " contribution_portal_file_id = :contributionFilePortalId";
    public static final String SELECT_CONTRIB_FILE_STS_CD_BY_ID =
            "select a.contribution_portal_file_id, a.status_code "
                    + " from contribution_service.contribution_portal_file a where"
                    + " a.contribution_portal_file_id=:contributionFileId";

    // TODO: the fund table BIS.CUST_FUND table need redo after the table is created in new schema
    public static final String SELECT_VALID_FUND_ID =
            "select 1 from "
                    + "dbo.clogExternalACH a, "
                    + "contribution_service.contribution_portal_file b "
                    + "where "
                    + "a.GroupID= :fundId "
                    + "and "
                    + "a.GroupID=b.customer_policy_number "
                    + "and "
                    + "b.contribution_portal_file_id=:contributionFileId";

    public static final String UPDATE_CONTRIB_FILE_APPROVED =
            "update contribution_service.contribution_portal_file set "
                    + "status_code=:statusCode, "
                    + "customer_fund_id=:fundId, "
                    + "approved_by_name=:userName, "
                    + "approved_timestamp=current_timestamp, "
                    + "effective_transaction_date=:effTransDate "
                    + "where "
                    + "contribution_portal_file_id=:contributionFileId";

    public static final String UPDATE_CONTRIB_FILE_DENIED =
            "update contribution_service.contribution_portal_file set "
                    + "status_code=:statusCode, "
                    + "deny_by_name =:userName, "
                    + "deny_comment_text=:comments, "
                    + "deny_timestamp=current_timestamp "
                    + "where "
                    + "contribution_portal_file_id=:contributionFileId";
}
