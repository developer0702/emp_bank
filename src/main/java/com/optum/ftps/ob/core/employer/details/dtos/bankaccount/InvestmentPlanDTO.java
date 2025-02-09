package com.optum.ftps.ob.core.employerDetails.dtos.bankaccount;

import lombok.Data;

@Data
public class InvestmentPlanDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerOrgAssoId;
    private String investmentPlanName;
    private int investmentAmount;
    private String investmentType;
    private String investmentDuration;
}
