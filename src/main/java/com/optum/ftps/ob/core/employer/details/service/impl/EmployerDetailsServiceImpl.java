package com.optum.ftps.ob.core.employer.details.service.impl;

import com.optum.ftps.ob.core.employer.details.dtos.EmployerDetailsResponseDTO;
import com.optum.ftps.ob.core.employer.details.service.EmployerDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployerDetailsServiceImpl implements EmployerDetailsService {

    @Override
    public EmployerDetailsResponseDTO getEmployerDetails(String searchField, boolean isEmpGroupId) {
        log.info("Getting employer details by ID or name, searchField: {}", searchField);
        var employerDetailsResponseDTO = new EmployerDetailsResponseDTO();
        log.debug("Returning employer details: {}", employerDetailsResponseDTO);
        return employerDetailsResponseDTO;
    }
}
