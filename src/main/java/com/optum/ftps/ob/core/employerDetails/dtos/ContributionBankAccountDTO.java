package com.optum.ftps.ob.core.employerDetails.dtos;

<<<<<<< Updated upstream
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
=======
public record ContributionBankAccountDTO(
        BankAccountIdentifierDTO bankAccountIdentifierDTO,
        BankAccountTypeCodeDTO bankAccountTypeCodeDTO,
        String bankName,
        String bankSequenceNumber,
        String bankAccountNickName,
        BankAccountStatusDTO bankAccountStatusDTO,
        String bankAccountOperation) {}
>>>>>>> Stashed changes
