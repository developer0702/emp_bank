package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionFileSummaryDTO;

public class ContributionSummaryDTOData {
    public static ContributionFileSummaryDTO getContributionSummaryDTOData() {
        ContributionFileSummaryDTO contributionFileSummaryDTO = new ContributionFileSummaryDTO();
        contributionFileSummaryDTO.setFileStatus(StatusDTOData.getStatusDTO());
        contributionFileSummaryDTO.setContributionFileInfo(
                ContributionFileInfoDTOData.getContributionFileInfoDTO());
        return contributionFileSummaryDTO;
    }
}
