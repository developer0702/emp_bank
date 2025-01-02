package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

import lombok.Data;

import java.util.List;

@Data
public class ContributionFileTransactionResponseDTO {
    private List<ContributionFileItemDTO> contributionFiles;
}
