package com.optum.ftps.ob.core.employerDetails.validator;

import com.optum.ftps.ob.core.constants.EmployerDetailsConstants;
import com.optum.ftps.ob.core.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.employerDetails.exceptions.ValidationException;
import com.optum.ftps.ob.core.employerDetails.model.v1.BankAccountStatus;
import com.optum.ftps.ob.core.employerDetails.model.v1.BankAccountTypeCode;
import com.optum.ftps.ob.core.employerDetails.model.v1.ContributionBankAccount;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployerBankDetailsValidator {

    private static final String CONTEXT_VALIDATE = "validate()";

    public Set<Integer> validateEmployerBankDetails(UpdateEmpBankDetailsRequest request)
            throws ValidationException {
        sanitizeRequest(request);
        Set<Integer> errors = new TreeSet<>();

        if (!checkRequiredFieldsPresent(request)) {
            log.error(
                    "{} - Inside validation Exception as required fields not present",
                    CONTEXT_VALIDATE);
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }
        if (!checkFieldsFormat(request)) {
            log.error("{} - Inside validation Exception for invalid format", CONTEXT_VALIDATE);
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }

        return errors;
    }

    private boolean checkRequiredFieldsPresent(UpdateEmpBankDetailsRequest request) {
        return !StringUtil.isEmpty(request.getRequestUserId())
                && !StringUtil.isEmpty(request.getSourceSystemId())
                && !StringUtil.isEmpty(request.getRequestId())
                && request.getEmployerBankDetail() != null
                && !StringUtil.isEmpty(request.getEmployerBankDetail().getEmployerGroupId())
                && checkRequiredContributionFieldsPresent(request);
    }

    private boolean checkRequiredContributionFieldsPresent(UpdateEmpBankDetailsRequest request) {
        boolean reqFieldPrst = false;
        List<ContributionBankAccount> contributionAccounts =
                request.getEmployerBankDetail().getContributionBankAccounts();
        for (ContributionBankAccount contributionBankAccount : contributionAccounts) {
            if (contributionBankAccount.getBankAccountIdentifier() != null
                    && contributionBankAccount.getBankAccountIdentifier().getBankAccountNumber()
                            != null
                    && contributionBankAccount.getBankAccountIdentifier().getBankRoutingNumber()
                            != null
                    && contributionBankAccount.getBankAccountTypeCode() != null
                    && contributionBankAccount.getBankAccountTypeCode().getCode() != null) {

                reqFieldPrst = true;
                break;
            }
        }
        return reqFieldPrst;
    }

    private boolean checkFieldsFormat(UpdateEmpBankDetailsRequest request)
            throws ValidationException {
        boolean flag = true;
        List<ContributionBankAccount> contributionAccounts =
                request.getEmployerBankDetail().getContributionBankAccounts();

        if (request.getRequestId() != null
                && request.getRequestId().trim().length()
                        > EmployerDetailsConstants.BNK_REQUESTID_LENGTH) {
            log.error(
                    "Length of RequestId should be less than or equal to 36**** {}",
                    request.getRequestId());
            flag = false;
        }

        if (request.getRequestUserId().trim().length()
                > EmployerDetailsConstants.BNK_REQ_USERID_LENGTH) {
            log.error(
                    "Length of RequestUserId should be less than or equal to 30**** {}",
                    request.getRequestUserId());
            flag = false;
        }

        if (request.getSourceSystemId().trim().length()
                > EmployerDetailsConstants.BNK_SRC_SYS_ID_LENGTH) {
            log.error(
                    "Length of SourceSystemId should be less than or equal to 10**** {}",
                    request.getSourceSystemId());
            flag = false;
        }

        if (request.getEmployerBankDetail().getEmployerGroupId().trim().length()
                        > EmployerDetailsConstants.EMP_GROUP_ID_LENGTH
                || !StringUtil.isAlphanumeric(
                        request.getEmployerBankDetail().getEmployerGroupId())) {
            log.error(
                    "Length of EmployerGroupId should be less than or equal to 9**** {}",
                    request.getEmployerBankDetail().getEmployerGroupId());
            flag = false;
        }

        for (ContributionBankAccount contributionBankAccount : contributionAccounts) {
            if (contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankRoutingNumber()
                                    .trim()
                                    .length()
                            != EmployerDetailsConstants.BNK_RTR_NO_LENGTH
                    || !StringUtil.isNumeric(
                            contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankRoutingNumber())) {
                log.error(
                        "Length of BankRoutingNumber should be equal to 9***** {}",
                        contributionBankAccount.getBankAccountIdentifier().getBankRoutingNumber());
                flag = false;
            }

            if (contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankAccountNumber()
                                    .trim()
                                    .length()
                            > EmployerDetailsConstants.BNK_ACCT_NO_LENGTH
                    || !StringUtil.isNumeric(
                            contributionBankAccount
                                    .getBankAccountIdentifier()
                                    .getBankAccountNumber())) {
                log.error(
                        "Length of BankAccountNumber should be less than or equal to 17 ******* {}",
                        contributionBankAccount.getBankAccountIdentifier().getBankAccountNumber());
                flag = false;
            }

            if (!StringUtil.isEmpty(contributionBankAccount.getBankName())
                    && contributionBankAccount.getBankName().trim().length()
                            > EmployerDetailsConstants.BNK_NAME_LENGTH) {
                log.error(
                        "Length of BankName should be less than equal to 40 {}",
                        contributionBankAccount.getBankName());
                flag = false;
            }

            if (!contributionBankAccount
                            .getBankAccountTypeCode()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_TYPE_CD_S)
                    && !contributionBankAccount
                            .getBankAccountTypeCode()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_TYPE_CD_C)) {
                log.error(
                        "BankAccountTypeCode should only be of 1 char Length[S (Savings) or C"
                                + " (Checking)] {}",
                        contributionBankAccount.getBankAccountTypeCode().getCode());
                flag = false;
            }

            if (!contributionBankAccount
                            .getBankAccountStatus()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_STS_CD_A)
                    && !contributionBankAccount
                            .getBankAccountStatus()
                            .getCode()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_STS_CD_I)) {
                log.error(
                        "BankAccountStsCode should only be of 1 char Length[A (Active) or I"
                                + " (Inactive)] {}",
                        contributionBankAccount.getBankAccountStatus().getCode());
                flag = false;
            }

            if (!contributionBankAccount
                            .getBankAccountOperation()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_OPERATION_CD_ADD)
                    && !contributionBankAccount
                            .getBankAccountOperation()
                            .trim()
                            .equalsIgnoreCase(
                                    EmployerDetailsConstants.BNK_ACCT_OPERATION_CD_UPDATE)) {
                throw new ValidationException(EmployerDetailsConstants.BNK_ACCNT_OPERATION_CD);
            }

            if (contributionBankAccount
                            .getBankAccountOperation()
                            .trim()
                            .equalsIgnoreCase(EmployerDetailsConstants.BNK_ACCT_OPERATION_CD_ADD)
                    && contributionBankAccount.getBankSequenceNumber() != null
                    && !contributionBankAccount.getBankSequenceNumber().trim().isEmpty()) {
                throw new ValidationException(EmployerDetailsConstants.CUST_FUND_ID_ERROR);
            }
        }

        if (contributionAccounts.isEmpty() || contributionAccounts.size() > 20) {
            throw new ValidationException(
                    String.valueOf(EmployerDetailsConstants.MAX_CONTRIBUTION_ACCOUNT_ALLOWED));
        }

        return flag;
    }

    private void sanitizeRequest(UpdateEmpBankDetailsRequest request) {
        request.setRequestId(StringUtil.sanitize(request.getRequestId()));
        request.setRequestUserId(StringUtil.sanitize(request.getRequestUserId()));
        request.setSourceSystemId(StringUtil.sanitize(request.getSourceSystemId()));
        request.getEmployerBankDetail()
                .setEmployerGroupId(
                        StringUtil.sanitize(request.getEmployerBankDetail().getEmployerGroupId()));
        for (ContributionBankAccount contributionBankAccount :
                request.getEmployerBankDetail().getContributionBankAccounts()) {
            contributionBankAccount
                    .getBankAccountIdentifier()
                    .setBankAccountNumber(
                            StringUtil.sanitize(
                                    contributionBankAccount
                                            .getBankAccountIdentifier()
                                            .getBankAccountNumber()));
            contributionBankAccount
                    .getBankAccountIdentifier()
                    .setBankRoutingNumber(
                            StringUtil.sanitize(
                                    contributionBankAccount
                                            .getBankAccountIdentifier()
                                            .getBankRoutingNumber()));
            contributionBankAccount.setBankName(
                    StringUtil.sanitize(contributionBankAccount.getBankName()));
            contributionBankAccount.setBankAccountTypeCode(
                    new BankAccountTypeCode(
                            StringUtil.sanitize(
                                    contributionBankAccount.getBankAccountTypeCode().getCode()),
                            contributionBankAccount.getBankAccountTypeCode().getCodeName()));
            contributionBankAccount.setBankAccountStatus(
                    new BankAccountStatus(
                            StringUtil.sanitize(
                                    contributionBankAccount.getBankAccountStatus().getCode()),
                            contributionBankAccount.getBankAccountStatus().getCodeName()));
            contributionBankAccount.setBankAccountOperation(
                    StringUtil.sanitize(contributionBankAccount.getBankAccountOperation()));
        }
    }
}
