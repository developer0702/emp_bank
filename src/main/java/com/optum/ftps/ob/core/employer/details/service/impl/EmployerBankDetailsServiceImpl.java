package com.optum.ftps.ob.core.employer.details.service.impl;

import com.optum.ftps.ob.core.employer.details.dtos.UpdateEmpBankDetailsDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employer.details.exceptions.ValidationException;
import com.optum.ftps.ob.core.employer.details.helper.BankAccountHelper;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsMapper;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;
import com.optum.ftps.ob.core.employer.details.service.EmployerBankDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

    private final BankAccountHelper bankAccountHelper;
    private final EmployerBankDetailsMapper employerBankDetailsMapper;

    @Override
    public UpdateEmpBankDetailsResponse updateEmployerBankDetailsServ(
            UpdateEmpBankDetailsDTO requestEntity) throws ValidationException {
        log.debug("Fetching bank details using Group ID: {}", requestEntity.getEmployerGroupId());

        // Step 1: Get Bank Details
        var bankAccountResponseDTO =
                bankAccountHelper.getBankDetailsByGroupId(requestEntity.getEmployerGroupId());

        if (bankAccountResponseDTO.isEmpty()) {
            log.error("No bank details found for Group ID: {}", requestEntity.getEmployerGroupId());
            throw new ValidationException("Bank details not found for given Group ID");
        }

        // Step 2: Ensure getData() is a List
        List<BankAccountDTO> accountList = bankAccountResponseDTO.get().getData();
        if (accountList == null || accountList.isEmpty()) {
            throw new ValidationException("No bank accounts found for the given Group ID.");
        }

        // Step 3: Check if provided bank details exist
        Optional<BankAccountDTO> matchingAccount =
                accountList.stream()
                        .filter(
                                account ->
                                        account.getAccountNumber() != null
                                                && account.getAccountNumber()
                                                        .equals(
                                                                requestEntity
                                                                        .getEmployerBankSeqNum()))
                        .findFirst();

        if (matchingAccount.isEmpty()) {
            log.warn("No matching bank account found for update. Skipping update.");
            throw new ValidationException("No matching bank account found for the given details.");
        }

        // Step 4: Perform Update
        var updatedBankAccountResponse =
                bankAccountHelper.updateBankAccount(
                        requestEntity.getEmployerGroupId(),
                        employerBankDetailsMapper.toBankAccountDTO(requestEntity));

        if (updatedBankAccountResponse.isEmpty()) {
            log.error(
                    "Failed to update bank details for Group ID: {}",
                    requestEntity.getEmployerGroupId());
            throw new RuntimeException("Failed to update bank details. Please try again.");
        }

        // Step 5: Convert response & return
        return employerBankDetailsMapper.toUpdateEmpBankDetailsResponse(
                updatedBankAccountResponse.get());
    }
}
