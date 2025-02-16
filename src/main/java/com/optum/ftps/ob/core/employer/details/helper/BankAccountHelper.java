package com.optum.ftps.ob.core.employer.details.helper;

import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountHelper {

    private static final String GET_BANK_ACCOUNT_BY_GROUP_ID =
            "/employer/{employerGroupId}/bank-account";
    private static final String GET_BANK_ACCOUNT_BY_EMPLOYER_AND_BANK_ID =
            "/employer/{employerId}/bank-account/{bankId}";
    private static final String UPDATE_BANK_ACCOUNT =
            "/employer/{employerGroupId}/bank-account/{bankId}";

    private final WebClient employerWebClient;

    /**
     * Fetches bank account details using Employer Group ID.
     *
     * @param employerGroupId the Employer Group ID
     * @return Optional of BankAccountResponseDTO
     */
    public Optional<BankAccountResponseDTO> getBankDetailsByGroupId(String employerGroupId) {
        try {
            return employerWebClient
                    .get()
                    .uri(
                            uriBuilder ->
                                    uriBuilder
                                            .path(GET_BANK_ACCOUNT_BY_GROUP_ID)
                                            .build(employerGroupId))
                    .retrieve()
                    .bodyToMono(BankAccountResponseDTO.class)
                    .blockOptional();
        } catch (Exception e) {
            log.error(
                    "Error fetching bank details for Group ID: {} - {}",
                    employerGroupId,
                    e.getMessage(),
                    e);
            return Optional.empty();
        }
    }

    /**
     * Updates the bank account details.
     *
     * @param employerGroupId Employer Group ID
     * @param bankAccountDTO  Bank account details to update
     * @return Optional of updated BankAccountResponseDTO
     */
    public Optional<BankAccountResponseDTO> updateBankAccount(
            String employerGroupId, BankAccountDTO bankAccountDTO) {
        try {
            return employerWebClient
                    .post()
                    .uri(
                            uriBuilder ->
                                    uriBuilder
                                            .path(UPDATE_BANK_ACCOUNT)
                                            .build(employerGroupId, bankAccountDTO.getBankId()))
                    .bodyValue(bankAccountDTO)
                    .retrieve()
                    .bodyToMono(BankAccountResponseDTO.class)
                    .blockOptional();
        } catch (Exception e) {
            log.error(
                    "Error updating bank account for Group ID: {} - {}",
                    employerGroupId,
                    e.getMessage(),
                    e);
            return Optional.empty();
        }
    }

    /**
     * Fetches bank account details using Employer ID and Bank ID.
     *
     * @param employerId Employer ID
     * @param bankId     Bank ID
     * @return Optional of BankAccountDTO
     */
    public Optional<BankAccountDTO> getBankDetailsByEmployerAndBankId(
            String employerId, String bankId) {
        try {
            return employerWebClient
                    .get()
                    .uri(
                            uriBuilder ->
                                    uriBuilder
                                            .path(GET_BANK_ACCOUNT_BY_EMPLOYER_AND_BANK_ID)
                                            .build(employerId, bankId))
                    .retrieve()
                    .bodyToMono(BankAccountDTO.class)
                    .blockOptional();
        } catch (Exception e) {
            log.error(
                    "Error fetching updated bank details for Employer ID: {}, Bank ID: {} - {}",
                    employerId,
                    bankId,
                    e.getMessage(),
                    e);
            return Optional.empty();
        }
    }

    /**
     * Updates the bank account details only if a matching account is found.
     *
     * @param employerGroupId Employer Group ID
     * @param bankAccountDTO  Bank account details to check and update
     * @return Optional of updated BankAccountResponseDTO
     */
    public Optional<BankAccountResponseDTO> updateBankAccountIfMatched(
            String employerGroupId, BankAccountDTO bankAccountDTO) {
        try {
            return getBankDetailsByGroupId(employerGroupId)
                    .flatMap(
                            bankAccountResponse ->
                                    bankAccountResponse.getData().stream()
                                            .filter(
                                                    existingBankAccount ->
                                                            existingBankAccount
                                                                    .getAccountNumber()
                                                                    .equals(
                                                                            bankAccountDTO
                                                                                    .getAccountNumber()))
                                            .findFirst()
                                            .map(
                                                    matchingBankAccount -> {
                                                        log.info(
                                                                "Match found for Account Number: {}"
                                                                    + " and Bank ID: {}. Updating"
                                                                    + " bank account...",
                                                                bankAccountDTO.getAccountNumber(),
                                                                bankAccountDTO.getBankId());
                                                        return updateBankAccount(
                                                                employerGroupId, bankAccountDTO);
                                                    }))
                    .orElseGet(
                            () -> {
                                log.warn(
                                        "No matching bank account found for Group ID: {}. Update"
                                                + " skipped.",
                                        employerGroupId);
                                return Optional.empty();
                            });
        } catch (Exception e) {
            log.error(
                    "Error while updating bank account for Group ID: {} - {}",
                    employerGroupId,
                    e.getMessage(),
                    e);
            return Optional.empty();
        }
    }
}
