package com.optum.ftps.ob.core.employer.details.service;

import com.optum.ftps.ob.core.employer.details.dtos.EmployerBankDetailsResponseDTO;

public interface EmployerBankDetailsService {

    EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO);

    EmployerBankDetailsResponseDTO addEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO);
}
