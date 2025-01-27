package com.optum.ftps.ob.core.employerDetails.service;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;

public interface EmployerBankDetailsService {

    EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO);
}
