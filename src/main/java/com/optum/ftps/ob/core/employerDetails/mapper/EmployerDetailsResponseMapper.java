package com.optum.ftps.ob.core.employerDetails.mapper;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.model.v1.EmployerDetailsResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerDetailsResponseMapper {
    @Mapping(target = "employer", source = "employerDTO")
    @Mapping(target = "status", source = "statusDTO")
    EmployerDetailsResponse employerDetailsResponse(
            EmployerDetailsResponseDTO employerDetailsResponseDTO);
}
