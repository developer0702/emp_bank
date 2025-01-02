package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;

import lombok.Data;

import java.util.List;

@Data
public class ContributionDetailsResponseDTO {
    private String requestId;
    private StatusDTO contrbDetailStatus;
    private List<ContributionFileDTO> contributionFiles;
}
