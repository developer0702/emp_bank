package com.optum.ftps.ob.core.employerDetails.repository;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerDTO;

import java.util.List;

public interface EmployerDetailsRepository {
    List<EmployerDTO> getEmployerDetails(String empGrpId);
}
