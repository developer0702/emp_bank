package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;

import lombok.Data;

@Data
public class ContributionFileSummaryDTO {
    private StatusDTO fileStatus;

    private ContrbutionFileInfoDTO contributionFileInfo;
}
