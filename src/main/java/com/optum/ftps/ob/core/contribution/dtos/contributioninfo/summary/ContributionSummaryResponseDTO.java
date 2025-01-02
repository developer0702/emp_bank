package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;

import lombok.Data;

import java.util.List;

@Data
public class ContributionSummaryResponseDTO {
    private StatusDTO status;
    private String requestId;
    private List<ContributionFileSummaryDTO> contributionFileSummary;
}
