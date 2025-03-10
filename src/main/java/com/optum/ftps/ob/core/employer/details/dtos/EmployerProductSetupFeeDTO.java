package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployerProductSetupFeeDTO {
    private EmployerProductSetupFeeCodeDTO employerProductSetupFeeCode;

    private BigDecimal employerProductSetupFeeAmount;
}
