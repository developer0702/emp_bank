package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmployerDTO {
    private String employerGroupId;

    private String employerName;

    private String employerSetupStatus;

    private String UHGLineOfBusiness;

    private AddressDTO address;

    private List<ContributionBankAccountDTO> contributionBankAccounts;

    private EmployerHDHPPolicyDTO employerHDHPPolicy;

    private PayerDetailDTO payerDetail;

    private String empBankFeeScheduleName;
}
