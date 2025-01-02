package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContrbutionFileInfoDTO;

import java.math.BigDecimal;

public class ContributionFileInfoDTOData {
    public static ContrbutionFileInfoDTO getContributionFileInfoDTO() {
        ContrbutionFileInfoDTO contributionFileInfoDTO = new ContrbutionFileInfoDTO();
        contributionFileInfoDTO.setContributionFileKey("123456");
        contributionFileInfoDTO.setInvalidTotalValidContributionAmount(BigDecimal.TEN);
        contributionFileInfoDTO.setValidRecordCount(BigDecimal.ONE);
        contributionFileInfoDTO.setEmployerGroupNumber("123456");
        contributionFileInfoDTO.setFundedIndicator("Y");
        contributionFileInfoDTO.setSubmittedDate("2021-01-01");
        contributionFileInfoDTO.setEffectiveDate("2021-01-01");
        contributionFileInfoDTO.setTotalValidContributionAmount("100.00");
        return contributionFileInfoDTO;
    }
}
