package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class InvestmentPlansDTO {

    private String primaryInvestmentPlanID;

    private String secondaryInvestmentPlanID;

    private Date effectiveDate;
}
