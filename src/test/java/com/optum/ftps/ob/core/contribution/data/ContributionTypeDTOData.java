package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.constants.ContributionType;
import com.optum.ftps.ob.core.contribution.constants.ContributionTypeCodes;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionTypeDTO;

public class ContributionTypeDTOData {
    public static ContributionTypeDTO getContributionTypeDTO() {
        ContributionTypeDTO contributionTypeDTO = new ContributionTypeDTO();
        contributionTypeDTO.setCode(ContributionTypeCodes.TYPE_CODE_001.getContributionTypeCode());
        contributionTypeDTO.setCodeName(ContributionType.EMPLOYER.getContributionTypeName());
        return contributionTypeDTO;
    }
}
