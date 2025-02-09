package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeIdSearchResponseDTO {
    private List<DataDTO> data;
    private String status;
    private List<ErrorDTO> errors;
}
