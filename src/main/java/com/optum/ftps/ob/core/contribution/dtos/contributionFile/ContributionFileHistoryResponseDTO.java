package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

import jakarta.validation.Valid;

import lombok.Data;

import java.util.List;

@Data
public class ContributionFileHistoryResponseDTO {
    private List<@Valid ContributionFileHistoryRecordsDTO> contributionFileHistoryRecords;
}
