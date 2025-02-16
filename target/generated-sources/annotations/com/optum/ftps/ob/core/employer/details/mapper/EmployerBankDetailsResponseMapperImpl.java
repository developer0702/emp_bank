package com.optum.ftps.ob.core.employer.details.mapper;

import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T10:36:27+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class EmployerBankDetailsResponseMapperImpl implements EmployerBankDetailsResponseMapper {

    @Override
    public UpdateEmpBankDetailsResponse map(UpdateEmpBankDetailsResponse responseDTO) {
        if ( responseDTO == null ) {
            return null;
        }

        UpdateEmpBankDetailsResponse.UpdateEmpBankDetailsResponseBuilder updateEmpBankDetailsResponse = UpdateEmpBankDetailsResponse.builder();

        updateEmpBankDetailsResponse.status( responseDTO.getStatus() );
        updateEmpBankDetailsResponse.message( responseDTO.getMessage() );
        updateEmpBankDetailsResponse.requestId( responseDTO.getRequestId() );

        return updateEmpBankDetailsResponse.build();
    }
}
