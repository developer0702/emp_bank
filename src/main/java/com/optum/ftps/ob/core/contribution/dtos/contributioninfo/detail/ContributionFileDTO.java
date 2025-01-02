package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;

import lombok.Data;

import java.util.List;

@Data
public class ContributionFileDTO {
    private String contributionFileId;

    private StatusDTO contributionFileStatus;

    private List<ContributionRecordDTO> contributionRecords;
}
