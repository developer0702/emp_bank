package com.optum.ftps.ob.core.employerDetails.dtos.bankaccount;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DataDTO {
    private int id;
    private OrganizationDTO organization;
    private EmployerDTO employer;
    private String groupId;
    private String status;
    private LocalDateTime hdhpEffectiveDate;
    private EnrollmentDTO enrollment;
    private InvestmentPlanDTO investmentPlan;
    private AccNumFileDTO accNumFile;
    private BranchPlanDTO branchPlan;
    private ApprovedBarExceptionDTO approvedBarException;
    private List<OfferingDTO> offerings;
}
