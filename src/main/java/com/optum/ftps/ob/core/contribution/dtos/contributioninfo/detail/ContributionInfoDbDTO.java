package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;

import lombok.Data;

import java.util.List;

@Data
public class ContributionInfoDbDTO {
    private List<ContributionDetailsDbDTO> contributionDetailsDbDTOList;
    private List<ContributionSummaryDbDTO> contributionSummaryDbDTOList;
}
