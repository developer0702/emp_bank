package com.optum.ftps.ob.core.employer.details.service;

import com.optum.ftps.ob.core.employer.details.dtos.UpdateEmpBankDetailsDTO;
import com.optum.ftps.ob.core.employer.details.exceptions.ValidationException;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;

public interface EmployerBankDetailsService {

    // EmployerBankDetailsResponseDTO updateEmployerBankDetails(EmployerBankDetailsResponseDTO
    // employerBankDetailsResponseDTO);

    UpdateEmpBankDetailsResponse updateEmployerBankDetailsServ(
            UpdateEmpBankDetailsDTO requestEntity) throws ValidationException;
}
