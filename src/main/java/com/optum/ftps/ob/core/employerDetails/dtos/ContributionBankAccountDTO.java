package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class ContributionBankAccountDTO {
    private BankAccountIdentifierDTO bankAccountIdentifier;
    private BankAccountTypeCodeDTO bankAccountTypeCode;
    private String bankName;
    private String bankSequenceNumber;
    private String bankAccountNickName;
    private BankAccountStatusDTO bankAccountStatus;
    private String bankAccountOperation;
}
