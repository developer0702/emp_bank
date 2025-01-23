 package com.optum.ftps.ob.core.employerDetails.validator;

 import com.optum.ftps.ob.core.constants.EmployerDetailsConstants;
 import com.optum.ftps.ob.core.constants.ErrorCodeConstants;
 import com.optum.ftps.ob.core.employerDetails.exceptions.ValidationException;
 import com.optum.ftps.ob.core.employerDetails.model.v1.ContributionBankAccount;
 import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
 import com.optum.ftps.ob.core.employerDetails.util.HSAStringUtil;

 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;

 import org.springframework.stereotype.Component;

 @Slf4j
 @Component
 @RequiredArgsConstructor
 public class EmployerBankDetailsValidator {

    /** Variable to hold context for validate method */
    private static final String CONTEXT_VALIDATE = "validate()";

    /**
     * This method is used to validate the fields while getting the account
     * information
     *
     * @param argRequestDetail Object
     * @throws ValidationException Validation Exception
     */
    public void validate(Object argRequestDetail) throws ValidationException {
        UpdateEmpBankDetailsRequest updEmpBankDetailsRequest =
                (UpdateEmpBankDetailsRequest) argRequestDetail;
        boolean isReqFieldsPresent = checkRequiredFieldsPresent(updEmpBankDetailsRequest);
        if (isReqFieldsPresent) {
            boolean validateFieldFormat = checkFieldsFormat(updEmpBankDetailsRequest);
            if (!validateFieldFormat) {
                log.error("{} - Inside validation Exception for invalid format",
 CONTEXT_VALIDATE);
                throw new ValidationException(ErrorCodeConstants.INCORRECT_FORMAT);
            }
        } else {
            log.error(
                    "{} - Inside validation Exception as required fields not present",
                    CONTEXT_VALIDATE);
            throw new ValidationException(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }
    }

    private boolean checkFieldsFormat(UpdateEmpBankDetailsRequest argUpdEmpBankDetailsRequest)
            throws ValidationException {
        boolean flag = true;
        ContributionBankAccount[] contributionAccounts =
                argUpdEmpBankDetailsRequest.getEmployerBankDetail().getContributionBankAccounts();

        if (argUpdEmpBankDetailsRequest.getRequestId() != null) {
            if (!(argUpdEmpBankDetailsRequest.getRequestId().trim().length()
                    <= EmployerDetailsConstants.BNK_REQUESTID_LENGTH)) {
                log.error(
                        "Length of RequestId should be less than or equal to 36**** {}",
                        argUpdEmpBankDetailsRequest.getRequestId());
                flag = false;
            }
        }

        if (!(argUpdEmpBankDetailsRequest.getRequestUserId().trim().length()
                <= EmployerDetailsConstants.BNK_REQ_USERID_LENGTH)) {
            log.error(
                    "Length of RequestUserId should be less than or equal to 30**** {}",
                    argUpdEmpBankDetailsRequest.getRequestUserId());
            flag = false;
        }
        if (!(argUpdEmpBankDetailsRequest.getSourceSystemId().trim().length()
                <= EmployerDetailsConstants.BNK_SRC_SYS_ID_LENGTH)) {
            log.error(
                    "Length of SourceSystemId should be less than or equal to 10**** {}",
                    argUpdEmpBankDetailsRequest.getSourceSystemId());
            flag = false;
        }
        if (!(argUpdEmpBankDetailsRequest
                                .getEmployerBankDetail()
                                .getEmployerGroupId()
                                .trim()
                                .length()
                        <= EmployerDetailsConstants.EMP_GROUP_ID_LENGTH)
                || !(HSAStringUtil.isAlphanumeric(
                        argUpdEmpBankDetailsRequest
                                .getEmployerBankDetail()
                                .getEmployerGroupId()))) {
            log.error(
                    "Length of EmployerGroupId should be less than or equal to 9**** {}",
                    argUpdEmpBankDetailsRequest.getEmployerBankDetail().getEmployerGroupId());
            flag = false;
        }
        for (ContributionBankAccount contributionBankAccount : contributionAccounts) {

            if (!(contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankRoutingNumber()
                                    .trim()
                                    .length()
                            == EmployerDetailsConstants.BNK_RTR_NO_LENGTH)
                    || !(HSAStringUtil.isNumeric(
                            contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankRoutingNumber()))) {
                log.error(
                        "Length of BankRoutingNumber should be equal to 9***** {}",

 contributionBankAccount.getBankAccountIdentifier().getBankRoutingNumber());
                flag = false;
            }
            if (!(contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankAccountNumber()
                                    .trim()
                                    .length()
                            <= EmployerDetailsConstants.BNK_ACCT_NO_LENGTH)
                    || !(HSAStringUtil.isNumeric(
                            contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankAccountNumber()))) {
                log.error(
                        "Length of BankAccountNumber should be less than or equal to 17 *******
 {}",

 contributionBankAccount.getBankAccountIdentifier().getBankAccountNumber());
                flag = false;
            }
            if (!HSAStringUtil.isEmpty(contributionBankAccount.getBankName())) {
                if (!(contributionBankAccount.getBankName().trim().length()
                        <= EmployerDetailsConstants.BNK_NAME_LENGTH)) {
                    log.error(
                            "Length of BankName should be less than equal to 40 {}",
                            contributionBankAccount.getBankName());
                    flag = false;
                }
            }
            if (!(contributionBankAccount
                            .getBankAccountTypeCode()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_TYPE_CD_S))
                    && !(contributionBankAccount
                            .getBankAccountTypeCode()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_TYPE_CD_C))) {
                log.error(
                        "BankAccountTypeCode should only be of 1 char Length[S (Savings) or C"
                                + " (Checking)] {}",
                        contributionBankAccount.getBankAccountTypeCode().getCode());
                flag = false;
            }
            if (!(contributionBankAccount
                            .getBankAccountStatus()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_STS_CD_A))
                    && !(contributionBankAccount
                            .getBankAccountStatus()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_STS_CD_I))) {
                log.error(
                        "BankAccountStsCode should only be of 1 char Length[A (Active) or I"
                                + " (Inactive)] {}",
                        contributionBankAccount.getBankAccountStatus().getCode());
                flag = false;
            }

            if (!(contributionBankAccount
                            .getBankAccountOperation()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_OPERATION_CD_ADD))
                    && !(contributionBankAccount
                            .getBankAccountOperation()
                            .trim()
                            .equalsIgnoreCase(
                                    EmployerDetailsConstants.BNK_ACCT_OPERATION_CD_UPDATE))) {
                throw new ValidationException(EmployerDetailsConstants.BNK_ACCNT_OPERATION_CD);
            }

            if (contributionBankAccount
                    .getBankAccountOperation()
                    .trim()
                    .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_OPERATION_CD_ADD)) {
                if (contributionBankAccount.getBankSequenceNumber() != null
                        && contributionBankAccount.getBankSequenceNumber().trim().length() > 0) {
                    throw new ValidationException(EmployerDetailsConstants.CUST_FUND_ID_ERROR);
                }
            }
        }
        if (contributionAccounts.length == 0 || contributionAccounts.length > 20) {
            throw new ValidationException(
                    EmployerDetailsConstants.MAX_CONTRIBUTION_ACCOUNT_ALLOWED);
        }
        return flag;
    }

    /**
     * This method is used check whether the input fields are present
     * @param argUpdEmpBankDetailsRequest UpdateEmpBankDetailsRequest
     * @return boolean reqFieldPrst
     */
    private boolean checkRequiredFieldsPresent(
            UpdateEmpBankDetailsRequest argUpdEmpBankDetailsRequest) {
        boolean reqFieldPrst =
                (!HSAStringUtil.isEmpty(argUpdEmpBankDetailsRequest.getRequestUserId()))
                        &&
 (!HSAStringUtil.isEmpty(argUpdEmpBankDetailsRequest.getSourceSystemId()))
                        && (argUpdEmpBankDetailsRequest.getEmployerBankDetail() != null)
                        && (!HSAStringUtil.isEmpty(
                                argUpdEmpBankDetailsRequest
                                        .getEmployerBankDetail()
                                        .getEmployerGroupId()))
                        && checkRequiredContributionFieldsPresent(argUpdEmpBankDetailsRequest);
        return reqFieldPrst;
    }

    private boolean checkRequiredContributionFieldsPresent(
            UpdateEmpBankDetailsRequest argUpdEmpBankDetailsRequest) {
        boolean reqFieldPrst = false;

        ContributionBankAccount[] contributionAccounts =
                argUpdEmpBankDetailsRequest.getEmployerBankDetail().getContributionBankAccounts();
        for (ContributionBankAccount contributionBankAccount : contributionAccounts) {

            if ((contributionBankAccount.getBankAccountIdentifier() != null)
                    && (contributionBankAccount.getBankAccountIdentifier().getBankAccountNumber()
                            != null)
                    && (contributionBankAccount.getBankAccountIdentifier().getBankRoutingNumber()
                            != null)
                    && (contributionBankAccount.getBankAccountTypeCode() != null)
                    && (contributionBankAccount.getBankAccountTypeCode().getCode() != null)) {
                reqFieldPrst = true;
                break;
            }
        }
        return reqFieldPrst;
    }
 }
