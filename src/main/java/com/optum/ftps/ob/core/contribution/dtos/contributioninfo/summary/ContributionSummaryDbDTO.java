package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContributionSummaryDbDTO {
    private String contributionFilePrtlId;
    private String customerPolicyNumber;
    private String submittedDate;
    private String effectiveDate;
    private int validRecordCount;
    private double totalValidContributionAmount;
    private int invalidRecordCount;
    private double invalidTotalValidContributionAmount;
    private String serviceReasonCode;
    private String serviceMessageSeverityCode;
    private String serviceMessageText;
}
