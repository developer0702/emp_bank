package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

@Data
public class EmployerIdSearchDTO {
    private String id;
    private String employerGroupId;
    private String status;
    private String employerName;
}
