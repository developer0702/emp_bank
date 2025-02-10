package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

@Data
public class BranchPlanDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerOrgAssoId;
    private String branchPlanInfoName;
    private int employerSubId;
    private String divClass;
    private String webBrand;
    private String cardBrand;
}
