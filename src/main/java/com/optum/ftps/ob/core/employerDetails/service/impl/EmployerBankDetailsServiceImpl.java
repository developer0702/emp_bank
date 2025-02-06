package com.optum.ftps.ob.core.employerDetails.service.impl;

import com.optum.ftps.ob.core.employerDetails.constants.BankAccountConstants;
import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountIdentifierDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.bankaccount.*;
import com.optum.ftps.ob.core.employerDetails.helper.BankAccountHelper;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerBankDetailsRepository;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

    private final EmployerBankDetailsRepository employerBankDetailsRepository;
    private final BankAccountHelper bankAccountHelper;

    @Override
    public EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailDTO) {
        log.info("Method Start ********** updEmployerBankDetails()::", employerBankDetailDTO);
        var employerBankDetailsResponseDTO = new EmployerBankDetailsResponseDTO();
        var empBankDetailDTO =
                employerBankDetailsRepository.updateEmployerBankDetails(
                        employerBankDetailDTO.getEmployerBankDetail());
        log.info("Method End ********** updEmployerBankDetails()::", empBankDetailDTO);
        employerBankDetailDTO.setEmployerBankDetail(empBankDetailDTO);
        employerBankDetailsResponseDTO.setEmployerBankDetail(empBankDetailDTO);
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

       var bankAccountDTO=createBankData(employerBankDetailsResponseDTO);

       var bankAccountResponseDTO = bankAccountHelper.addBankAccountResponse(bankAccountDTO,employeeId);

       if( Objects.nonNull(bankAccountResponseDTO) && bankAccountResponseDTO.getStatus().equalsIgnoreCase("SUCCESS") && bankAccountResponseDTO.getData()>0){
          var bankAccountResponse=  bankAccountHelper.getBankAccountInfoFromBankService(employeeId,bankAccountResponseDTO.getData());

         if  (Objects.nonNull(bankAccountResponse)){
             ContributionBankAccountDTO contributionBankAccountDTO=new ContributionBankAccountDTO();
             BankAccountIdentifierDTO bankAccountIdentifierDTO=new BankAccountIdentifierDTO();
             bankAccountIdentifierDTO.setBankAccountNumber(bankAccountResponse.getAccountNumber());
             bankAccountIdentifierDTO.setBankRoutingNumber(bankAccountResponse.getRoutingNumber());
             contributionBankAccountDTO.setBankAccountIdentifier(bankAccountIdentifierDTO);
             contributionBankAccountDTO.setBankAccountNickName(bankAccountResponse.getNickName());
             contributionBankAccountDTO.setBankName(bankAccountResponse.getBankName());
             contributionBankAccountDTO.setBankSequenceNumber(String.valueOf(bankAccountResponse.getId()));

             List<ContributionBankAccountDTO> contributionBankAccounts=new ArrayList<>();
             contributionBankAccounts.add(contributionBankAccountDTO);
             EmployerBankDetailDTO employerBankDetailDTO=new EmployerBankDetailDTO();
             employerBankDetailDTO.setContributionBankAccounts(contributionBankAccounts);
             employerBankDetailsResponseDTO.setEmployerBankDetail(employerBankDetailDTO);
             employerBankDetailsResponseDTO.setRequestUserId(employerBankDetailsResponseDTO.getRequestUserId());
             employerBankDetailsResponseDTO.setRequestId(employerBankDetailsResponseDTO.getRequestId());
             employerBankDetailsResponseDTO.setSourceSystemId(employerBankDetailsResponseDTO.getSourceSystemId());
             return employerBankDetailsResponseDTO;
         }

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
            bankAccountDTO.setUsage(BankAccountConstants.HSA_FUNDING);
            bankAccountDTO.setSource(BankAccountConstants.EUREKA);
            bankAccountDTO.setCorrelationId("1234");
        }
       return bankAccountDTO;
    }

    public ContributionBankAccountDTO getContributionInfo(){

    }


}
