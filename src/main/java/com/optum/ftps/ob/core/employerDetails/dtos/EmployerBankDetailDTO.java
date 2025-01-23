package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class EmployerBankDetailDTO {
    private ContributionBankAccountDTO contributionBankAccount;
    private String employerGroupId;
}
