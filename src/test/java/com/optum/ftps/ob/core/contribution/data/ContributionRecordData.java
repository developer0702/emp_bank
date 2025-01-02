package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionRecordDTO;

import java.math.BigDecimal;

public class ContributionRecordData {
    public static ContributionRecordDTO getContributionRecordDTO() {
        ContributionRecordDTO contributionRecordDTO = new ContributionRecordDTO();
        contributionRecordDTO.setContributionAmount(BigDecimal.ONE);
        contributionRecordDTO.setContributionType(ContributionTypeDTOData.getContributionTypeDTO());
        return contributionRecordDTO;
    }
}
