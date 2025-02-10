package com.optum.ftps.ob.core.employer.details.mapper;

import com.optum.ftps.ob.core.employer.details.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerBankDetailsMapper {
    @Mapping(target = "employerBankDetail", source = "request")
    EmployerBankDetailsResponseDTO map(UpdateEmpBankDetailsRequest request);
}
