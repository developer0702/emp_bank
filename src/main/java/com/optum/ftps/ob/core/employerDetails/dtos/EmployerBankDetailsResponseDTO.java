package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class EmployerBankDetailsResponseDTO {
    private EmployerBankDetailDTO employerBankDetail;
    private String requestId;
    private String requestUserId;
    private String sourceSystemId;
}
