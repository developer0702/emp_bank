package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ContributionRecordDTO {
    private String accountHolderSsn;

    private PersonNameDTO personName;

    private BigDecimal contributionAmount;

    private ContributionTypeDTO contributionType;

    private String validIndicator;

    private List<InvalidReasonDTO> invalidReasons;
}
