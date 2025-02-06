package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

@Data
public class EmployerBankDetailsResponseDTO {
    private EmployerBankDetailDTO employerBankDetail;
    private String requestId;
    private String requestUserId;
    private String sourceSystemId;
}
