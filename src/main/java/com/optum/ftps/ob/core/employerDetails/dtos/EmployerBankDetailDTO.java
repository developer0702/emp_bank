package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmployerBankDetailDTO {
    private List<ContributionBankAccountDTO> contributionBankAccounts;
    private String employerGroupId;
}
