package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmployerDetailsResponseDTO {
    private StatusDTO statusDTO;
    private List<EmployerDTO> employerDTO;
}
