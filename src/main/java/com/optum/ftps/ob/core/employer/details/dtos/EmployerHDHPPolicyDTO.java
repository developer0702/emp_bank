package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class EmployerHDHPPolicyDTO {
    private Date hDHPEffectiveDate;

    private Date hDHPCaseSoldDate;
}
