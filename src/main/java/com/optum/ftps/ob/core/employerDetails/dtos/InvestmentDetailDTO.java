package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvestmentDetailDTO {
    private String autoSweepDesc;

    private BigDecimal minTransferAmount;

    private InvestmentPlansDTO investmentPlans;
}
