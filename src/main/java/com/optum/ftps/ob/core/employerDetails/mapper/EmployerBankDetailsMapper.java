package com.optum.ftps.ob.core.employerDetails.mapper;

import com.optum.ftps.ob.core.employer.details1.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerBankDetailsMapper {
   // @Mapping(target = "employerBankDetail", source = "request")
    EmployerBankDetailsResponseDTO map(UpdateEmpBankDetailsRequest request);
}
