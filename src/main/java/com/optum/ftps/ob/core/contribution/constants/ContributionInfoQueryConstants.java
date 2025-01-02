package com.optum.ftps.ob.core.contribution.constants;

import static com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants.CONTRIBUTION_SERVICE_SCHEMA_NAME;
import static com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants.FTPS_SCHEMA_NAME;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContributionInfoQueryConstants {

    public static final String CONTRIBUTION_FILE_TABLE_NAME =
            CONTRIBUTION_SERVICE_SCHEMA_NAME + ".ach_contribution_file  Z, ";
    public static final String GET_VALID_CONTRIBUTION_RECS =
            "SELECT A.ach_contribution_id , Z.contribution_portal_file_id , A.ssn, B.FirstName"
                    + " , B.MiddleInitial , B.LastName , A.contribution_amount,"
                    + " A.contribution_type_name , A.valid_code, A.contribution_year FROM "
                    + CONTRIBUTION_FILE_TABLE_NAME
                    + " contribution_service.ach_contribution_file_record A,  dbo.AccountsFTPS B"
                    + " WHERE A.ach_contribution_file_id  = Z.ach_contribution_file_id AND  B.SSN ="
                    + " A.ssn AND Z.contribution_portal_file_id =:contributionFileId AND"
                    + " A.valid_code = 'V' ORDER BY 3 ";

    public static final String GET_INAVLID_CONTRIBUTION_RECS =
            "SELECT A.ach_contribution_id, Z.contribution_portal_file_id, A.ssn, B.FirstName,"
                + " B.MiddleInitial, B.LastName, A.contribution_amount, A.contribution_type_name,"
                + " A.valid_code, A.contribution_year, C.error_code, D.error_text FROM "
                    + CONTRIBUTION_FILE_TABLE_NAME
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".ach_contribution_file_record A LEFT OUTER JOIN "
                    + FTPS_SCHEMA_NAME
                    + ".AccountsFTPS B "
                    + "ON B.SSN = A.ssn, "
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".contribution_record_error C, "
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".error_type D WHERE A.ach_contribution_file_id  = Z.ach_contribution_file_id"
                    + "  AND C.ach_contribution_id = A.ach_contribution_id AND D.error_code ="
                    + " C.error_code AND Z.contribution_portal_file_id = :contributionFileId AND"
                    + " A.valid_code = 'I' ORDER BY 3 ";

    public static final String GET_ALL_CONTRIBUTION_RECS =
            "SELECT A.ach_contribution_id, Z.contribution_portal_file_id, A.ssn, B.FirstName,"
                + " B.MiddleInitial, B.LastName, A.contribution_amount, A.contribution_type_name,"
                + " A.valid_code, A.contribution_year, C.error_code, D.error_text FROM "
                    + CONTRIBUTION_FILE_TABLE_NAME
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".ach_contribution_file_record A LEFT OUTER JOIN "
                    + FTPS_SCHEMA_NAME
                    + ".AccountsFTPS B "
                    + "ON B.SSN = A.ssn, "
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".contribution_record_error C, "
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".error_type D WHERE A.ach_contribution_file_id = Z.ach_contribution_file_id"
                    + " AND C.ach_contribution_id = A.ach_contribution_id AND D.error_code ="
                    + " C.error_code AND Z.contribution_portal_file_id = :contributionFileId  AND"
                    + " A.valid_code = 'I' UNION  SELECT A.ach_contribution_id,"
                    + " Z.contribution_portal_file_id, A.ssn, B.FirstName, B.MiddleInitial,"
                    + " B.LastName, A.contribution_amount, A.contribution_type_name, A.valid_code,"
                    + " A.contribution_year,'' as error_code, '' as error_text FROM "
                    + CONTRIBUTION_FILE_TABLE_NAME
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".ach_contribution_file_record A, "
                    + FTPS_SCHEMA_NAME
                    + ".AccountsFTPS B WHERE A.ach_contribution_file_id ="
                    + " Z.ach_contribution_file_id AND B.SSN = A.ssn AND"
                    + " Z.contribution_portal_file_id = :contributionFileId AND A.valid_code = 'V'"
                    + " ORDER BY 3 ";

    public static final String GET_CONTRIBUTION_SUMMARY_COLUMNS =
            "SELECT A.ach_contribution_file_id, "
                    + "A.customer_policy_number, "
                    + "A.original_submitted_timestamp, "
                    + "A.effective_transaction_date, "
                    + "A.valid_record_count, "
                    + "A.valid_total_contribution_amount, "
                    + "A.invalid_record_count, "
                    + "A.invalid_total_contribution_amount, "
                    + "B.service_reason_code,  "
                    + "B.service_message_severity_code,  "
                    + "B.SERVICE_MESSAGE_TEXT  "
                    + "FROM "
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".ach_contribution_file A  "
                    + "LEFT OUTER JOIN "
                    + CONTRIBUTION_SERVICE_SCHEMA_NAME
                    + ".service_message_type B ON B.service_message_id= A.service_message_id "
                    + "WHERE "
                    + " A.customer_policy_number = :employerGroupId ";

    /** Query to get the funded files for the given date range, based on file effective date */
    public static final String GET_FUNDIND_Y_DATE_RANGE_MAX_NO_OF_FILES =
            GET_CONTRIBUTION_SUMMARY_COLUMNS
                    + " AND A.effective_transaction_date >= :dateFrom AND"
                    + " A.effective_transaction_date <= :dateTo  ORDER BY"
                    + " A.effective_transaction_date DESC, A.original_submitted_timestamp DESC";

    /**
     * Query to get all files (funded, non-funded) for the given date range, based on file submitted
     * date
     */
    public static final String GET_FUNDIND_N_DATE_RANGE_MAX_NO_OF_FILES =
            GET_CONTRIBUTION_SUMMARY_COLUMNS
                    + "AND DATE(A.original_submitted_timestamp) >= :dateFrom AND"
                    + " DATE(A.original_submitted_timestamp) <= :dateTo ORDER BY"
                    + " A.original_submitted_timestamp DESC";

    /** Query to get the funded files restricting to max number of files */
    public static final String GET_FUNDIND_Y_MAX_NO_OF_FILES =
            GET_CONTRIBUTION_SUMMARY_COLUMNS
                    + "AND A.effective_transaction_date <= :dateTo ORDER BY"
                    + " A.effective_transaction_date DESC, A.original_submitted_timestamp DESC";

    /**
     * Query to all files (funded, non-funded) restricting to max number of files based on request
     * parameter
     */
    public static final String GET_FUNDIND_N_MAX_NO_OF_FILES =
            GET_CONTRIBUTION_SUMMARY_COLUMNS + "ORDER BY A.original_submitted_timestamp DESC";
}
