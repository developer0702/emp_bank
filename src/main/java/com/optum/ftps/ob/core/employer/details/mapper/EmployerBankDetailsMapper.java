package com.optum.ftps.ob.core.employer.details.mapper;

import com.optum.ftps.ob.core.employer.details.dtos.UpdateEmpBankDetailsDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountResponseDTO;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerBankDetailsMapper {

    UpdateEmpBankDetailsDTO map(UpdateEmpBankDetailsRequest request);

    @Mapping(target = "accountNumber", source = "employerAccNum")
    @Mapping(target = "routingNumber", source = "employerRoutingNum")
    @Mapping(target = "bankName", source = "employerBankName")
    @Mapping(target = "nickName", source = "employerAccNickName")
    @Mapping(target = "accountStatus", source = "employerAccStatCode")
    BankAccountDTO toBankAccountDTO(UpdateEmpBankDetailsDTO requestForUpate);

    UpdateEmpBankDetailsResponse toUpdateEmpBankDetailsResponse(
            BankAccountResponseDTO bankAccountResponseDTO);

    // 🔹 Custom method to handle JsonNullable<String> to String mapping
    default String map(JsonNullable<String> value) {
        return value != null && value.isPresent() ? value.get() : null;
    }
}
