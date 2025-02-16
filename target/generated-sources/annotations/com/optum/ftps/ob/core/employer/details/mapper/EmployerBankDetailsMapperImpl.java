package com.optum.ftps.ob.core.employer.details.mapper;

import com.optum.ftps.ob.core.employer.details.dtos.UpdateEmpBankDetailsDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountResponseDTO;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T10:36:26+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class EmployerBankDetailsMapperImpl implements EmployerBankDetailsMapper {

    @Override
    public UpdateEmpBankDetailsDTO map(UpdateEmpBankDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        UpdateEmpBankDetailsDTO.UpdateEmpBankDetailsDTOBuilder updateEmpBankDetailsDTO = UpdateEmpBankDetailsDTO.builder();

        updateEmpBankDetailsDTO.requestId( request.getRequestId() );
        updateEmpBankDetailsDTO.employerGroupId( request.getEmployerGroupId() );
        updateEmpBankDetailsDTO.employerBankSeqNum( request.getEmployerBankSeqNum() );
        updateEmpBankDetailsDTO.employerAccNum( map( request.getEmployerAccNum() ) );
        updateEmpBankDetailsDTO.employerRoutingNum( map( request.getEmployerRoutingNum() ) );
        updateEmpBankDetailsDTO.employerBankName( map( request.getEmployerBankName() ) );
        updateEmpBankDetailsDTO.employerAccNickName( map( request.getEmployerAccNickName() ) );
        updateEmpBankDetailsDTO.employerAccStatCode( map( request.getEmployerAccStatCode() ) );
        updateEmpBankDetailsDTO.employerAccOperation( map( request.getEmployerAccOperation() ) );

        return updateEmpBankDetailsDTO.build();
    }

    @Override
    public BankAccountDTO toBankAccountDTO(UpdateEmpBankDetailsDTO requestForUpate) {
        if ( requestForUpate == null ) {
            return null;
        }

        BankAccountDTO bankAccountDTO = new BankAccountDTO();

        bankAccountDTO.setAccountNumber( requestForUpate.getEmployerAccNum() );
        bankAccountDTO.setRoutingNumber( requestForUpate.getEmployerRoutingNum() );
        bankAccountDTO.setBankName( requestForUpate.getEmployerBankName() );
        bankAccountDTO.setNickName( requestForUpate.getEmployerAccNickName() );
        bankAccountDTO.setAccountStatus( requestForUpate.getEmployerAccStatCode() );

        return bankAccountDTO;
    }

    @Override
    public UpdateEmpBankDetailsResponse toUpdateEmpBankDetailsResponse(BankAccountResponseDTO bankAccountResponseDTO) {
        if ( bankAccountResponseDTO == null ) {
            return null;
        }

        UpdateEmpBankDetailsResponse.UpdateEmpBankDetailsResponseBuilder updateEmpBankDetailsResponse = UpdateEmpBankDetailsResponse.builder();

        updateEmpBankDetailsResponse.status( bankAccountResponseDTO.getStatus() );

        return updateEmpBankDetailsResponse.build();
    }
}
