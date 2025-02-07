package com.optum.ftps.ob.core.employerDetails.dtos.bankaccount;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeIdSearchResponseDTO {
    private List<DataDTO> data;
    private String status;
    private List<ErrorDTO> errors;
}
