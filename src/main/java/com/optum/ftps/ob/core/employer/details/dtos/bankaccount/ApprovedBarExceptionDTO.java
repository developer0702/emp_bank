package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

@Data
public class ApprovedBarExceptionDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerOrgAssoId;
    private int barExceptionReqFlag;
    private String barExceptionComments;
}
