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
  public EmployerDetailsResponseDTO getEmployerDetails(String searchField, boolean isEmpGroupId) {
    log.info("Getting employer details by ID or name, searchField: {}", searchField);
    var employerDetailsResponseDTO = new EmployerDetailsResponseDTO();
    var employerDTO = employerDetailsRepository.getEmployerDetails(searchField, isEmpGroupId);
    employerDetailsResponseDTO.setEmployerDTO(employerDTO);
    log.debug("Returning employer details: {}", employerDetailsResponseDTO);
    return employerDetailsResponseDTO;
  }
}
