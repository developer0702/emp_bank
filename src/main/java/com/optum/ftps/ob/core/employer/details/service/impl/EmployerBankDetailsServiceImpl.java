package com.optum.ftps.ob.core.employer.details.service.impl;

<<<<<<< HEAD:src/main/java/com/optum/ftps/ob/core/employer/details/service/impl/EmployerBankDetailsServiceImpl.java
import com.optum.ftps.ob.core.employer.details.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employer.details.service.EmployerBankDetailsService;
=======
import com.optum.ftps.ob.core.employerDetails.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.bankaccount.*;
import com.optum.ftps.ob.core.employerDetails.helper.BankAccountHelper;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerBankDetailsRepository;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;
>>>>>>> c7651db (aggregateSearch):src/main/java/com/optum/ftps/ob/core/employerDetails/service/impl/EmployerBankDetailsServiceImpl.java

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

<<<<<<< HEAD:src/main/java/com/optum/ftps/ob/core/employer/details/service/impl/EmployerBankDetailsServiceImpl.java
=======
    private final EmployerBankDetailsRepository employerBankDetailsRepository;
    private final BankAccountHelper bankAccountHelper;

>>>>>>> c7651db (aggregateSearch):src/main/java/com/optum/ftps/ob/core/employerDetails/service/impl/EmployerBankDetailsServiceImpl.java
    @Override
    public EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailDTO) {
        log.info("Method Start ********** updEmployerBankDetails()::", employerBankDetailDTO);
        var employerBankDetailsResponseDTO = new EmployerBankDetailsResponseDTO();
        employerBankDetailsResponseDTO.setRequestUserId(employerBankDetailDTO.getRequestUserId());
        employerBankDetailsResponseDTO.setRequestId(employerBankDetailDTO.getRequestId());
        employerBankDetailsResponseDTO.setSourceSystemId(employerBankDetailDTO.getSourceSystemId());
        log.debug("Returning employer details: {}", employerBankDetailsResponseDTO);
        return employerBankDetailsResponseDTO;
    }

    @Override
    public EmployerBankDetailsResponseDTO addEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO) {


        var employerIdSearchDTO = new EmployerIdSearchDTO();

        String str=employerBankDetailsResponseDTO.getEmployerBankDetail().getEmployerGroupId();
        log.debug(str);
        employerIdSearchDTO.setGroupId(str);
        var  response=bankAccountHelper.getAggregatorServiceResponse(employerIdSearchDTO);
       List<DataDTO> data= response.getData();
       EmployerDTO employerDTO= data.get(0).getEmployer();
       int employeeId=employerDTO.getId();
      // List<BankAccountDTO> bankAccountDTOList=employerDTO.getBankAccounts();
       //if(bankAccountDTOList!=null && bankAccountDTOList.isEmpty()){
         //  if (bankAccountDTOList>0 )
       //}
       var bankAccountDTO=createBankData(employerBankDetailsResponseDTO);

       var bankAccountResponseDTO = bankAccountHelper.addBankAccountResponse(bankAccountDTO,employeeId);

       if( Objects.nonNull(bankAccountResponseDTO) && bankAccountResponseDTO.getStatus().equalsIgnoreCase("SUCCESS") && bankAccountResponseDTO.getData()>0){
          var bankAccountResponse=  bankAccountHelper.getBankAccountInfoFromBankService(employeeId,bankAccountResponseDTO.getData());

           return employerBankDetailsResponseDTO;
}
        return null;
    }

    private BankAccountDTO createBankData(EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO) {
        var bankAccountDTO=new BankAccountDTO();
        List<ContributionBankAccountDTO> contributionBankAccounts= employerBankDetailsResponseDTO.getEmployerBankDetail().getContributionBankAccounts();
        for(ContributionBankAccountDTO contributionBankAccountDTO:contributionBankAccounts){
            bankAccountDTO.setBankName(contributionBankAccountDTO.getBankName());
            bankAccountDTO.setBankName(contributionBankAccountDTO.getBankAccountNickName());
            bankAccountDTO.setAccountNumber(contributionBankAccountDTO.getBankAccountIdentifier().getBankAccountNumber());
            bankAccountDTO.setAccountNumber(contributionBankAccountDTO.getBankAccountIdentifier().getBankRoutingNumber());
            bankAccountDTO.setAccountStatus(contributionBankAccountDTO.getBankAccountTypeCode().getCode());
            bankAccountDTO.setAccountStatus(contributionBankAccountDTO.getBankAccountStatus().getCodeName());
            bankAccountDTO.setUsage("HSA_FUNDING");
            bankAccountDTO.setSource("EUREKA");
            bankAccountDTO.setCorrelationId("1234");
        }
       return bankAccountDTO;
    }


}
