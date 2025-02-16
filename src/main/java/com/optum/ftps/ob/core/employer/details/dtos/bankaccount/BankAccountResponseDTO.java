package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.util.List;

@Data
public class BankAccountResponseDTO {
    private List<BankAccountDTO> data;
       private String status;
//    private List<ErrorDTO> errors;
}
