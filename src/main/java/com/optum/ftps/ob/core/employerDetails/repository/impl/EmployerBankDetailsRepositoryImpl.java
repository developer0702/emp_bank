package com.optum.ftps.ob.core.employerDetails.repository.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountIdentifierDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountStatusDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.BankAccountTypeCodeDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailDTO;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerBankDetailsDbToDTOMapper;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerBankDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class EmployerBankDetailsRepositoryImpl implements EmployerBankDetailsRepository {

    private final EmployerBankDetailsDbToDTOMapper employerBankDetailsMapper;

    @Override
    public EmployerBankDetailDTO updateEmployerBankDetails(
            EmployerBankDetailDTO employerBankDetailDTO) {
        log.debug("Updating employer bank details: {}", employerBankDetailDTO);

        // Dummy response
        ContributionBankAccountDTO contributionBankAccount = new ContributionBankAccountDTO();
        BankAccountIdentifierDTO bankAccountIdentifierDTO = new BankAccountIdentifierDTO();
        bankAccountIdentifierDTO.setBankAccountNumber("1234567890");
        bankAccountIdentifierDTO.setBankRoutingNumber("123456789");
        contributionBankAccount.setBankAccountIdentifier(bankAccountIdentifierDTO);
        BankAccountTypeCodeDTO bankAccountTypeCodeDTO = new BankAccountTypeCodeDTO();
        bankAccountTypeCodeDTO.setCode("S");
        bankAccountTypeCodeDTO.setCodeName("Savings");
        contributionBankAccount.setBankAccountTypeCode(bankAccountTypeCodeDTO);
        contributionBankAccount.setBankName("USA Bank");
        contributionBankAccount.setBankSequenceNumber("123456");
        contributionBankAccount.setBankAccountNickName("Dummy Account");
        BankAccountStatusDTO bankAccountStatusDTO = new BankAccountStatusDTO();
        bankAccountStatusDTO.setCode("A");
        bankAccountStatusDTO.setCodeName("Active");
        contributionBankAccount.setBankAccountStatus(bankAccountStatusDTO);
        contributionBankAccount.setBankAccountOperation("Dummy Operation");

        EmployerBankDetailDTO dummyResponse = new EmployerBankDetailDTO();
        dummyResponse.setContributionBankAccounts(List.of(contributionBankAccount));
        dummyResponse.setEmployerGroupId("DummyGroupId");
        log.info("Employer bank details updated: {}", dummyResponse);
        return dummyResponse;
    }
}
