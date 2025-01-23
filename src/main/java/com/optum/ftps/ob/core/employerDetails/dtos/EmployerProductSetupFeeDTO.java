package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployerProductSetupFeeDTO {
    private EmployerProductSetupFeeCodeDTO employerProductSetupFeeCode;

    private BigDecimal employerProductSetupFeeAmount;
}
