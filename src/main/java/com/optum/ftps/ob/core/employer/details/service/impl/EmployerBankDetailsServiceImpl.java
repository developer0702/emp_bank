package com.optum.ftps.ob.core.employer.details.service.impl;

import com.optum.ftps.ob.core.employer.details.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.employer.details.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.EmployerBankDetailsResponseDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.DataDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.EmployerDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.EmployerIdSearchDTO;
import com.optum.ftps.ob.core.employer.details.exceptions.NotFoundException;
import com.optum.ftps.ob.core.employer.details.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.employer.details.helper.BankAccountHelper;
import com.optum.ftps.ob.core.employer.details.service.EmployerBankDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

    private final BankAccountHelper bankAccountHelper;
//    private final WebClient webClient;

    @Override
    public EmployerBankDetailsResponseDTO updateEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailDTO) {
        var employerIdSearchDTO = new EmployerIdSearchDTO();

        String str = employerBankDetailDTO.getEmployerBankDetail().getEmployerGroupId();
        log.debug(str);
        employerIdSearchDTO.setGroupId(str);
        var response = bankAccountHelper.getAggregatorServiceResponse(employerIdSearchDTO);
        if (Objects.isNull(response)
                || Objects.isNull(response.getData())
                || response.getData().isEmpty()) {
            log.error("Employer not found");
            ErrorItem errorItem =
                    ErrorItem.builder()
                            .statusCode(ErrorCodeConstants.RECORD_NOT_FOUND_ERROR_CODE)
                            .severity("ERROR")
                            .statusDescription("Employer not found")
                            .build();
            List<ErrorItem> errorItems = List.of(errorItem);
            throw new NotFoundException(errorItems);
        }
        List<DataDTO> data = response.getData();
        EmployerDTO employerDTO = data.getFirst().getEmployer();
        int employeeId = employerDTO.getId();
        int bankAccountID = employerDTO.getBankAccounts().getFirst().getId();

        List<ContributionBankAccountDTO> contributionBankAccounts =
                employerBankDetailDTO
                        .getEmployerBankDetail()
                        .getContributionBankAccounts();


        for (ContributionBankAccountDTO contributionBankAccountDTO : contributionBankAccounts) {
            if ("Update".equalsIgnoreCase(contributionBankAccountDTO.getBankAccountOperation())) {
                 bankAccountID = employerDTO.getBankAccounts().stream()
                        .filter(bankAccount -> bankAccount.getAccountNumber().equals(contributionBankAccountDTO.getBankAccountIdentifier().getBankAccountNumber()))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException(List.of(
                                ErrorItem.builder()
                                        .statusCode(ErrorCodeConstants.RECORD_NOT_FOUND_ERROR_CODE)
                                        .severity("ERROR")
                                        .statusDescription("Bank account not found")
                                        .build()
                        ))).getId();

                var bankAccountDTO = createBankData(employerBankDetailDTO);

                var bankAccountResponseDTO =
                        bankAccountHelper.updateBankAccountResponse(
                                bankAccountDTO, employeeId, bankAccountID);

                if (Objects.isNull(bankAccountResponseDTO)
                        || !bankAccountResponseDTO.getStatus().equalsIgnoreCase("SUCCESS")
                        || bankAccountResponseDTO.getData() <= 0) {
                    log.error("Error in updating bank account details");
                    return null;
                }
            }
        }

        return employerBankDetailDTO;
    }

    @Override
    public EmployerBankDetailsResponseDTO addEmployerBankDetails(
            EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO) {

        var employerIdSearchDTO = new EmployerIdSearchDTO();

        String str = employerBankDetailsResponseDTO.getEmployerBankDetail().getEmployerGroupId();
        log.debug(str);
        employerIdSearchDTO.setGroupId(str);
        var response = bankAccountHelper.getAggregatorServiceResponse(employerIdSearchDTO);
        List<DataDTO> data = response.getData();
        EmployerDTO employerDTO = data.get(0).getEmployer();
        int employeeId = employerDTO.getId();
        // List<BankAccountDTO> bankAccountDTOList=employerDTO.getBankAccounts();
        // if(bankAccountDTOList!=null && bankAccountDTOList.isEmpty()){
        //  if (bankAccountDTOList>0 )
        // }
        var bankAccountDTO = createBankData(employerBankDetailsResponseDTO);

        var bankAccountResponseDTO =
                bankAccountHelper.addBankAccountResponse(bankAccountDTO, employeeId);

        if (Objects.nonNull(bankAccountResponseDTO)
                && bankAccountResponseDTO.getStatus().equalsIgnoreCase("SUCCESS")
                && bankAccountResponseDTO.getData() > 0) {
            var bankAccountResponse =
                    bankAccountHelper.getBankAccountInfoFromBankService(
                            employeeId, bankAccountResponseDTO.getData());

            return employerBankDetailsResponseDTO;
        }
        return null;
    }

    private BankAccountDTO createBankData(
            EmployerBankDetailsResponseDTO employerBankDetailsResponseDTO) {
        var bankAccountDTO = new BankAccountDTO();
        List<ContributionBankAccountDTO> contributionBankAccounts =
                employerBankDetailsResponseDTO
                        .getEmployerBankDetail()
                        .getContributionBankAccounts();
        for (ContributionBankAccountDTO contributionBankAccountDTO : contributionBankAccounts) {
            bankAccountDTO.setBankName(contributionBankAccountDTO.getBankName());
            bankAccountDTO.setBankName(contributionBankAccountDTO.getBankAccountNickName());
            bankAccountDTO.setAccountNumber(
                    contributionBankAccountDTO.getBankAccountIdentifier().getBankAccountNumber());
            bankAccountDTO.setAccountNumber(
                    contributionBankAccountDTO.getBankAccountIdentifier().getBankRoutingNumber());
            bankAccountDTO.setAccountStatus(
                    contributionBankAccountDTO.getBankAccountTypeCode().getCode());
            bankAccountDTO.setAccountStatus(
                    contributionBankAccountDTO.getBankAccountStatus().getCodeName());
            bankAccountDTO.setUsage("HSA_FUNDING");
            bankAccountDTO.setSource("EUREKA");
            bankAccountDTO.setCorrelationId("1234");
        }
        return bankAccountDTO;
    }
}

