package com.optum.ftps.ob.core.contribution.service;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;

public interface ContributionInfoService {

    ContributionDetailsResponseDTO contributionDetails(
            ContributionDetailsRequestDTO contributionDetailsRequestDTO);

    ContributionSummaryResponseDTO contributionSummary(
            ContributionSummaryRequestDTO contributionSummaryRequestDTO);
}
