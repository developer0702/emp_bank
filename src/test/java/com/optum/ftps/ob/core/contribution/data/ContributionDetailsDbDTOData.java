package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.constants.ContributionType;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;

public class ContributionDetailsDbDTOData {

    public static ContributionDetailsDbDTO getContributionDetailDbDTO() {
        return ContributionDetailsDbDTO.builder()
                .achContributionId("123")
                .contributionType(ContributionType.EMPLOYER.getContributionTypeName())
                .contributionFilePrtId("file1")
                .contributionYear(ContributionType.CURRENT_YEAR.getContributionTypeName())
                .accountHolderFirstNm("firstname")
                .accountHolderLastNm("lastname")
                .accountHolderMilddleNm("middlename")
                .build();
    }
}
