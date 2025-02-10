package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.util.List;

@Data
public class BankAccountResponseDTO {
    private int data;
    private String status;
    private List<ErrorDTO> errors;
}

