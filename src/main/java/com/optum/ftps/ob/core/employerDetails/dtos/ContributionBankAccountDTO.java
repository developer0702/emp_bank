package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class ContributionBankAccountDTO {
  private BankAccountIdentifierDTO bankAccountIdentifierDTO;
  private BankAccountTypeCodeDTO bankAccountTypeCodeDTO;
  private String bankName;
  private String bankSequenceNumber;
  private String bankAccountNickName;
  private BankAccountStatusDTO bankAccountStatusDTO;
  private String bankAccountOperation;
}
