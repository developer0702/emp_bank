package com.optum.ftps.ob.core.employerDetails.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailDTO;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface EmployerBankDetailsMapper {

    EmployerBankDetailDTO map(UpdateEmpBankDetailsRequest request);
}
