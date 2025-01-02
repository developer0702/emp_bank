package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;

import java.math.BigDecimal;

public class ContributionSummaryDbDTOData {
    public static ContributionSummaryDbDTO getContributionSummaryDbDTO() {
        ContributionSummaryDbDTO contributionSummaryDbDTO =
                ContributionSummaryDbDTO.builder().build();
        contributionSummaryDbDTO.setContributionFilePrtlId("123456");
        contributionSummaryDbDTO.setInvalidTotalValidContributionAmount(
                BigDecimal.TEN.doubleValue());
        contributionSummaryDbDTO.setValidRecordCount(BigDecimal.ONE.intValue());
        contributionSummaryDbDTO.setCustomerPolicyNumber("123456");
        contributionSummaryDbDTO.setSubmittedDate("2021-01-01");
        contributionSummaryDbDTO.setEffectiveDate("2021-01-01");
        contributionSummaryDbDTO.setTotalValidContributionAmount(100);
        contributionSummaryDbDTO.setServiceMessageSeverityCode("INFO");
        contributionSummaryDbDTO.setServiceMessageText("Service message text");
        contributionSummaryDbDTO.setServiceReasonCode("INFO");
        return contributionSummaryDbDTO;
    }
}
