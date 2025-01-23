package com.optum.ftps.ob.core.employerDetails.service.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerBankDetailsRepository;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

    private final EmployerBankDetailsRepository employerBankDetailsRepository;

    @Override
    public EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailDTO employerBankDetailDTO) {
        log.info("Method Start ********** updEmployerBankDetails()::", employerBankDetailDTO);
        var employerBankDetailsResponseDTO = new EmployerBankDetailsResponseDTO();
        var empBankDetailDTO =
                employerBankDetailsRepository.updateEmployerBankDetails(employerBankDetailDTO);
        employerBankDetailsResponseDTO.setEmployerBankDetail(empBankDetailDTO);
        log.debug("Returning employer details: {}", employerBankDetailsResponseDTO);
        return employerBankDetailsResponseDTO;
    }
}
