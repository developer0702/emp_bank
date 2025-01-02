package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionFileDTO;

import java.util.List;

public class ContributionFileDTOData {
    public static ContributionFileDTO getContributionFileDTO() {
        ContributionFileDTO contributionFileDTO = new ContributionFileDTO();
        contributionFileDTO.setContributionFileId("file1");
        contributionFileDTO.setContributionRecords(
                List.of(ContributionRecordData.getContributionRecordDTO()));
        return contributionFileDTO;
    }
}
