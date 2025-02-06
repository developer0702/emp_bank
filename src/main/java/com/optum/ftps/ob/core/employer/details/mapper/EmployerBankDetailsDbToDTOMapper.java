package com.optum.ftps.ob.core.employer.details.mapper;

import static com.optum.ftps.ob.core.employer.details.util.DbUtil.getString;

import com.optum.ftps.ob.core.employer.details.dtos.BankAccountIdentifierDTO;
import com.optum.ftps.ob.core.employer.details.dtos.BankAccountStatusDTO;
import com.optum.ftps.ob.core.employer.details.dtos.BankAccountTypeCodeDTO;
import com.optum.ftps.ob.core.employer.details.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.EmployerBankDetailDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployerBankDetailsDbToDTOMapper {

    public List<EmployerBankDetailDTO> mapToEmployerBankDetailsResponseDTO(List<Object[]> dbData) {

        var employerBankDetails = new ArrayList<EmployerBankDetailDTO>();

        if (!dbData.isEmpty()) {
            for (Object[] data : dbData) {
                var employerBankDTO = new EmployerBankDetailDTO();

                var bankAccountDTO = new ContributionBankAccountDTO();
                bankAccountDTO.setBankAccountIdentifier(
                        new BankAccountIdentifierDTO(getString(data, 1), getString(data, 2)));
                bankAccountDTO.setBankAccountTypeCode(
                        new BankAccountTypeCodeDTO(getString(data, 2), getString(data, 4)));
                bankAccountDTO.setBankName(getString(data, 5));
                bankAccountDTO.setBankSequenceNumber(getString(data, 6));
                bankAccountDTO.setBankAccountNickName(getString(data, 7));
                bankAccountDTO.setBankAccountStatus(
                        new BankAccountStatusDTO(getString(data, 8), getString(data, 9)));
                bankAccountDTO.setBankAccountOperation(getString(data, 10));

                employerBankDTO.setContributionBankAccounts(List.of(bankAccountDTO));
                employerBankDTO.setEmployerGroupId(getString(data, 11));
                employerBankDetails.add(employerBankDTO);
            }
        }
        return employerBankDetails;
    }
}
