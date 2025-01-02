package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary;

import lombok.Data;

@Data
public class ContributionSummaryRequestDTO {
    private String employerGroupId;
    private String restrictToFunded;
    private String dateFrom;
    private String dateTo;
    private Integer maxNumberOfFiles;
    private String requestId;
}
