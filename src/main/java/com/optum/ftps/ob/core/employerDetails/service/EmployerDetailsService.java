package com.optum.ftps.ob.core.employerDetails.service;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerDetailsResponseDTO;

public interface EmployerDetailsService {
    EmployerDetailsResponseDTO getEmployerDetails(String searchField, boolean isEmpGroupId);
}
