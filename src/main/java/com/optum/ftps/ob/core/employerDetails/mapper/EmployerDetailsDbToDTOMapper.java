package com.optum.ftps.ob.core.employerDetails.mapper;

import static com.optum.ftps.ob.core.employerDetails.util.DbUtil.getDate;
import static com.optum.ftps.ob.core.employerDetails.util.DbUtil.getString;

import com.optum.ftps.ob.core.employerDetails.dtos.AddressDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.AddressLinesDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.AddressPostalCodeDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.AddressStateDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.AddressTypeCodeDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountIdentifierDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountStatusDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountTypeCodeDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerHDHPPolicyDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.PayerDetailDTO;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployerDetailsDbToDTOMapper {
    public List<EmployerDTO> mapToEmployerDetailsResponseDTO(List<Object[]> dbData)
            throws ParseException {
        var employerDetails = new ArrayList<EmployerDTO>();
        if (!dbData.isEmpty()) {
            for (Object[] data : dbData) {
                var employerDTO = new EmployerDTO();
                employerDTO.setEmployerGroupId(getString(data, 0));
                employerDTO.setEmployerName(getString(data, 1));
                employerDTO.setEmployerSetupStatus(getString(data, 2));
                employerDTO.setUHGLineOfBusiness(getString(data, 3));

                var addressDTO = new AddressDTO();
                addressDTO.setPrimaryAddressIndicator(getString(data, 4));
                var addresslinesDTO = new AddressLinesDTO();
                addresslinesDTO.setAddressDeliveryLine1(getString(data, 5));
                addresslinesDTO.setAddressDeliveryLine2(getString(data, 6));
                addresslinesDTO.setAddressCity(getString(data, 7));
                addresslinesDTO.setAddressState(
                        new AddressStateDTO(getString(data, 8), getString(data, 9)));
                addresslinesDTO.setAddressPostalCode(
                        new AddressPostalCodeDTO(
                                getString(data, 10), getString(data, 11), getString(data, 12)));
                addressDTO.setAddressTypeCode(
                        new AddressTypeCodeDTO(getString(data, 13), getString(data, 14)));
                addressDTO.setAddressLines(addresslinesDTO);
                employerDTO.setAddress(addressDTO);

                var bankAccountDTO = new ContributionBankAccountDTO();
                bankAccountDTO.setBankAccountIdentifier(
                        new BankAccountIdentifierDTO(getString(data, 15), getString(data, 16)));
                bankAccountDTO.setBankAccountTypeCode(
                        new BankAccountTypeCodeDTO(getString(data, 17), getString(data, 18)));
                bankAccountDTO.setBankName(getString(data, 19));
                bankAccountDTO.setBankSequenceNumber(getString(data, 20));
                bankAccountDTO.setBankAccountNickName(getString(data, 21));
                bankAccountDTO.setBankAccountStatus(
                        new BankAccountStatusDTO(getString(data, 22), getString(data, 23)));
                bankAccountDTO.setBankAccountOperation(getString(data, 24));
                employerDTO.setContributionBankAccounts(List.of(bankAccountDTO));

                var hdhpPolicyDTO = new EmployerHDHPPolicyDTO();
                hdhpPolicyDTO.setHDHPEffectiveDate(getDate(data, 25));
                hdhpPolicyDTO.setHDHPCaseSoldDate(getDate(data, 26));
                employerDTO.setEmployerHDHPPolicy(hdhpPolicyDTO);

                var payerDetailDTO = new PayerDetailDTO();
                payerDetailDTO.setBranchNumber(getString(data, 27));
                payerDetailDTO.setPayerName(getString(data, 28));
                employerDTO.setPayerDetail(payerDetailDTO);

                employerDTO.setEmpBankFeeScheduleName(getString(data, 29));

                employerDetails.add(employerDTO);
            }
        }
        return employerDetails;
    }
}
