package com.optum.ftps.ob.core.employer.details.service;

import com.optum.ftps.ob.core.employer.details.dtos.EmployerDetailsResponseDTO;

public interface EmployerDetailsService {
    EmployerDetailsResponseDTO getEmployerDetails(String searchField, boolean isEmpGroupId);
}
