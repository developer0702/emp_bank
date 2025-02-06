package com.optum.ftps.ob.core.employerDetails.dtos.bankaccount;

import lombok.Data;

import java.util.List;
@Data
public class BankAccountResponseDTO {
    private int data;
    private String status;
    private List<ErrorDTO> errors;
}

