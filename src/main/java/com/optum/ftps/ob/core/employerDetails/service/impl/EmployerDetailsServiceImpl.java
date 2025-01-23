package com.optum.ftps.ob.core.employerDetails.service.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerDetailsRepository;
import com.optum.ftps.ob.core.employerDetails.service.EmployerDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployerDetailsServiceImpl implements EmployerDetailsService {
    private final EmployerDetailsRepository employerDetailsRepository;

    @Override
    public EmployerDetailsResponseDTO getEmployerDetailsById(String empGroupId) {
        log.info("Getting employer details by ID, empGroupId: {}", empGroupId);
        var employerDetailsResponseDTO = new EmployerDetailsResponseDTO();
        var employerDTO = employerDetailsRepository.getEmployerDetails(empGroupId);
        employerDetailsResponseDTO.setEmployerDTO(employerDTO);
        log.debug("Returning employer details: {}", employerDetailsResponseDTO);
        return employerDetailsResponseDTO;
    }
}
