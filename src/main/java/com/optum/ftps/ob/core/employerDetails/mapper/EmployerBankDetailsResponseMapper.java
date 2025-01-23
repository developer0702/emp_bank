package com.optum.ftps.ob.core.employerDetails.mapper;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployerBankDetailsResponseMapper {
  UpdateEmpBankDetailsResponse map(EmployerBankDetailsResponseDTO responseDTO);
}
