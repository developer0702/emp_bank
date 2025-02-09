package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;

@Data
class OrganizationDTO {
    private int id;
    private String name;
    private String branchPlanName;
    private int branchId;
    private int planId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
