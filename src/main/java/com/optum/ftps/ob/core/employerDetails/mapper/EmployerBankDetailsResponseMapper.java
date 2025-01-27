package com.optum.ftps.ob.core.employerDetails.mapper;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerBankDetailsResponseMapper {
    //    @Mapping(target = "employerBankDetails", source = "employerBankDetailDTO")
    UpdateEmpBankDetailsResponse map(EmployerBankDetailsResponseDTO responseDTO);
}
