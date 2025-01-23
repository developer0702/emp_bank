package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class EmployerBankDetailsRequestDTO {
    private EmployerBankDetailDTO employerBankDetail;
    private String requestId;
    private String requestUserId;
    private String sourceSystemId;
}
