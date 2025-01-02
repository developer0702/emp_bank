package com.optum.ftps.ob.core.contribution.service.impl;

import static com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants.RESPONSE_CODE_FIFTEEN;
import static com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants.RESPONSE_CODE_FIFTEEN_VALUE;
import static com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants.RESPONSE_CODE_SIXTEEN;
import static com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants.RESPONSE_CODE_SIXTEEN_VALUE;
import static com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants.RESPONSE_SEVERITY_INF;
import static com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants.RECORD_NOT_FOUND_ERROR_CODE;
import static com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants.SERVICE_FAILED_ERROR_CODE;

import com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants;
import com.optum.ftps.ob.core.contribution.constants.FileStatusCode;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileTransactionResponseDTO;
import com.optum.ftps.ob.core.contribution.exceptions.ContributionFileStatusException;
import com.optum.ftps.ob.core.contribution.exceptions.NotFoundException;
import com.optum.ftps.ob.core.contribution.exceptions.ServiceException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.contribution.repository.ContributionFileRepository;
import com.optum.ftps.ob.core.contribution.service.ContributionFileService;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ContributionFileServiceImpl implements ContributionFileService {

    private final ExceptionService exceptionService;
    private final ContributionFileRepository contributionFileRepository;

    @Override
    public ContributionFileHistoryResponseDTO getEmployerContributionFileHistory(
            String customerPolicyNumber, String effectiveTransactionDate) {
        log.info(
                "getEmployerContributionFileHistory called with customerPolicyNumber: {} and"
                        + " effectiveTransactionDate: {}",
                customerPolicyNumber,
                effectiveTransactionDate);
        var contributionFileHistoryResponseDTO = new ContributionFileHistoryResponseDTO();
        var dbData =
                contributionFileRepository.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);
        contributionFileHistoryResponseDTO.setContributionFileHistoryRecords(dbData);
        return contributionFileHistoryResponseDTO;
    }

    @Override
    public ContributionFileNamesResponseDTO getContributionFileNames(
            List<String> contributionFilePortalId) {
        log.info("Getting contribution file names for {}", contributionFilePortalId);
        var result = new ContributionFileNamesResponseDTO();
        result.setContributionFileNames(
                contributionFileRepository.getContributionFileNames(contributionFilePortalId));
        log.trace("Returning contribution file names: {}", result);
        return result;
    }

    @Override
    public ContributionFileTransactionResponseDTO getEmployerContributionFileTransactions(
            String contributionFilePortalId) {
        log.info("Getting contribution file transactions for {}", contributionFilePortalId);
        var result = new ContributionFileTransactionResponseDTO();
        result.setContributionFiles(
                contributionFileRepository.getEmployerContributionFileTransactions(
                        contributionFilePortalId));
        log.trace("Returning contribution file transactions: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public ContributionFileStatusResponseDTO updateContributionFileStatus(
            ContributionFileStatusDTO contributionFileStatusDTO) {
        log.info(
                "updateContributionFileStatus called with fileId: {}",
                contributionFileStatusDTO.contributionFileId());

        checkExistingStatus(contributionFileStatusDTO);

        FileStatusCode statusCode =
                FileStatusCode.byCode(contributionFileStatusDTO.fileStatusCode());
        log.info("FileStatusCode: {}", statusCode);

        int count;

        if (FileStatusCode.APPROVED.equals(statusCode)) {
            count = handleApprovalRequest(contributionFileStatusDTO);
        } else {
            count = handleDenialRequest(contributionFileStatusDTO);
        }

        if (count == 0) {
            var errorItem = exceptionService.getError(RECORD_NOT_FOUND_ERROR_CODE);
            throw new NotFoundException(List.of(errorItem));
        }

        return ContributionFileStatusResponseDTO.builder()
                .contributionFileId(contributionFileStatusDTO.contributionFileId())
                .build();
    }

    int handleApprovalRequest(ContributionFileStatusDTO contributionFileStatusDTO) {
        // Disabling the fund validation until the BIS.CUST_FUND table is migrated to the new schema
        // checkFundStatus(contributionFileStatusDTO);
        try {
            return contributionFileRepository.approveContributionFile(contributionFileStatusDTO);
        } catch (RuntimeException e) {
            var errorItem = exceptionService.getError(SERVICE_FAILED_ERROR_CODE);
            throw new ServiceException(List.of(errorItem));
        }
    }

    int handleDenialRequest(ContributionFileStatusDTO contributionFileStatusDTO) {
        try {
            return contributionFileRepository.denyContributionFile(contributionFileStatusDTO);
        } catch (RuntimeException e) {
            var errorItem = exceptionService.getError(SERVICE_FAILED_ERROR_CODE);
            throw new ServiceException(List.of(errorItem));
        }
    }

    void checkFundStatus(ContributionFileStatusDTO contributionFileStatusDTO) {
        var valid = contributionFileRepository.isValidFundId(contributionFileStatusDTO);
        if (!valid) {
            log.error("Fund ID is not valid: {}", contributionFileStatusDTO.contributionFileId());
            throwStatusException(RESPONSE_CODE_SIXTEEN, RESPONSE_CODE_SIXTEEN_VALUE);
        }
    }

    void checkExistingStatus(ContributionFileStatusDTO contributionFileStatusDTO) {
        var dbData =
                contributionFileRepository.getContributionFileStatus(contributionFileStatusDTO);
        for (var data : dbData) {
            if (!ContributionFileConstants.STATUS_PENDING.equals(data.statusCode())) {
                log.error(
                        "File status is not pending: {}",
                        contributionFileStatusDTO.contributionFileId());
                throwStatusException(RESPONSE_CODE_FIFTEEN, RESPONSE_CODE_FIFTEEN_VALUE);
            }
        }
    }

    void throwStatusException(String code, String description) {
        var errorItem =
                ErrorItem.builder()
                        .statusCode(code)
                        .statusDescription(description)
                        .severity(RESPONSE_SEVERITY_INF)
                        .build();
        throw new ContributionFileStatusException(List.of(errorItem));
    }
}
