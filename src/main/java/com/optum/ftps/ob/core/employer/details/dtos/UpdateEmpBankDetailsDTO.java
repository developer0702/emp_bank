package com.optum.ftps.ob.core.employer.details.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateEmpBankDetailsDTO {
    @NotBlank(message = "Request ID is required")
    private String requestId;

    @NotBlank(message = "Employer Group ID is required")
    private String employerGroupId;

    @NotBlank(message = "Employer Bank Sequence Number is required")
    private String employerBankSeqNum;

    private String employerAccNum;         // Nullable
    private String employerRoutingNum;     // Nullable
    private String employerBankName;       // Nullable
    private String employerAccNickName;    // Nullable
    private String employerAccStatCode;    // Nullable
    private String employerAccOperation;   // Nullable
}
