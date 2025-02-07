package com.optum.ftps.ob.core.employerDetails.service.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

    @Override
    public EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailDTO) {
        log.info("Method Start ********** updEmployerBankDetails()::", employerBankDetailDTO);
        var employerBankDetailsResponseDTO = new EmployerBankDetailsResponseDTO();


        employerBankDetailsResponseDTO.setRequestUserId(employerBankDetailDTO.getRequestUserId());
        employerBankDetailsResponseDTO.setRequestId(employerBankDetailDTO.getRequestId());
        employerBankDetailsResponseDTO.setSourceSystemId(employerBankDetailDTO.getSourceSystemId());
        log.debug("Returning employer details: {}", employerBankDetailsResponseDTO);
        return employerBankDetailsResponseDTO;
    }
}
