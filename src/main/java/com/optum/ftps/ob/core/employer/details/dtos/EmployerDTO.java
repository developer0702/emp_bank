package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmployerDTO {
    private String employerGroupId;

    private String employerName;

    private String employerSetupStatus;

    private String uHGLineOfBusiness;

    private AddressDTO address;

    private List<ContributionBankAccountDTO> contributionBankAccounts;

    private EmployerHDHPPolicyDTO employerHDHPPolicy;

    private PayerDetailDTO payerDetail;

    private String empBankFeeScheduleName;
}
