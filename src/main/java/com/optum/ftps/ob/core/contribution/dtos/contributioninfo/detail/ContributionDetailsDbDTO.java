package com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContributionDetailsDbDTO {
    private String achContributionId;
    private String contributionFilePrtId;
    private String accountHolderSSN;
    private String accountHolderFirstNm;
    private String accountHolderMilddleNm;
    private String accountHolderLastNm;
    private double contributionAmount;
    private String contributionType;
    private String validIndicator;
    private String contributionYear;
    private String errorCode;
    private String errorText;
    private List<InvalidReasonDTO> invalidReasonList;
}
