package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContributionDetailsRequestDTO {
    private String requestId;
    private List<FileDetailsDTO> fileDetails = new ArrayList<>();
}
