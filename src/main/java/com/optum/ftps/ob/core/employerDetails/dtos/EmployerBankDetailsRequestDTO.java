package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class EmployerBankDetailsRequestDTO {
    private ContributionBankAccountDTO contributionBankAccount;
    private String employerGroupId;
    private String requestId;
    private String requestUserId;
    private String sourceSystemId;
}
