package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContrbutionFileInfoDTO {
    private String contributionFileKey;
    private String employerGroupNumber;
    private String fundedIndicator;
    private String submittedDate;
    private String effectiveDate;
    private BigDecimal validRecordCount;
    private String totalValidContributionAmount;
    private BigDecimal invalidRecordCount;
    private BigDecimal invalidTotalValidContributionAmount;
}
