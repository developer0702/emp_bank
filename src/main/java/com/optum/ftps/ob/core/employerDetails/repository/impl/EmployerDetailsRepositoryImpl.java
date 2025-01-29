package com.optum.ftps.ob.core.employerDetails.repository.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerDTO;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerDetailsDbToDTOMapper;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployerDetailsRepositoryImpl implements EmployerDetailsRepository {
    private final EmployerDetailsDbToDTOMapper employerDetailsDbToDTOMapper;

    @Override
    public List<EmployerDTO> getEmployerDetails(String searchField, boolean isEmpGroupId) {
        List<Object[]> dbData = new ArrayList<>();
        dbData.add(
                new Object[] {
                    "4H9960",
                    "BMC SOUTHEAST",
                    "Active",
                    "Small Business",
                    "Y",
                    "1406 HAYS ST UNIT 8",
                    "TEXAS",
                    "TALLAHASSEE",
                    "FL",
                    "521344",
                    "0010",
                    "32301",
                    "0000",
                    "B",
                    "Business",
                    "Debit Card after Sig",
                    "No checks",
                    "Web",
                    "Batch",
                    "N",
                    "N",
                    "Y",
                    "Y",
                    "B",
                    "No checks",
                    "2024-06-06",
                    "2024-06-08",
                    "003",
                    "UHC KEY AND SMALL",
                    "Batch CES"
                });
        log.debug("Employer details fetched from DB: {}", dbData);
        try {
            var result = employerDetailsDbToDTOMapper.mapToEmployerDetailsResponseDTO(dbData);
            return result;
        } catch (ParseException e) {
            log.error("Error parsing date", e);
            return new ArrayList<>();
        }
    }
}
