package com.optum.ftps.ob.core.contribution.controller;

import com.optum.ftps.ob.core.contribution.api.v1.ContributionsApi;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileHistoryResponseMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileNamesResponseMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileStatusMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileStatusResponseMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileTransactionResponseMapper;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileHistoryResponse;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileNamesResponse;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileTransactionResponse;
import com.optum.ftps.ob.core.contribution.model.v1.UpdateContributionFileStatusRequest;
import com.optum.ftps.ob.core.contribution.model.v1.UpdateContributionFileStatusResponse;
import com.optum.ftps.ob.core.contribution.service.ContributionFileService;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;
import com.optum.ftps.ob.core.contribution.util.StringUtil;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileHistoryValidator;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileNamesRequestValidator;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileStatusValidator;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileTransactionValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContributionFileController implements ContributionsApi {
    private final ContributionFileHistoryValidator contributionFileHistoryValidator;
    private final ContributionFileService contributionFileService;
    private final ContributionFileNamesResponseMapper contributionFileNamesResponseMapper;
    private final ContributionFileNamesRequestValidator contributionFileNamesRequestValidator;
    private final ExceptionService exceptionService;
    private final ContributionFileHistoryResponseMapper contributionFileHistoryResponseMapper;
    private final ContributionFileTransactionResponseMapper
            contributionFileTransactionResponseMapper;
    private final ContributionFileTransactionValidator contributionFileTransactionValidator;
    private final ContributionFileStatusMapper contributionFileStatusMapper;
    private final ContributionFileStatusResponseMapper contributionFileStatusResponseMapper;
    private final ContributionFileStatusValidator contributionFileStatusValidator;

    @Override
    public ResponseEntity<ContributionFileHistoryResponse> getEmployerContributionFileHistory(
            String customerPolicyNumber, String effectiveTransactionDate) {
        customerPolicyNumber = StringUtil.sanitize(customerPolicyNumber);
        effectiveTransactionDate = StringUtil.sanitize(effectiveTransactionDate);
        log.debug(
                "Received request to get contribution file history customer policy number: {} "
                        + "and effective transaction date :{}",
                customerPolicyNumber,
                effectiveTransactionDate);
        var errors =
                contributionFileHistoryValidator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);
        exceptionService.handleValidationError(errors);
        var contributionFileHistoryResponseDTO =
                contributionFileService.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);
        return ResponseEntity.ok(
                contributionFileHistoryResponseMapper.contributionFileHistoryResponse(
                        contributionFileHistoryResponseDTO));
    }

    @Override
    public ResponseEntity<ContributionFileNamesResponse> getEmployerContributionFileNames(
            List<String> contributionFilePortalId) {
        contributionFilePortalId =
                contributionFileNamesRequestValidator.validate(contributionFilePortalId);
        log.info("Getting contribution file names for {}", contributionFilePortalId);
        var contributionFileNamesResponseDTO =
                contributionFileService.getContributionFileNames(contributionFilePortalId);
        return new ResponseEntity<>(
                contributionFileNamesResponseMapper.mapContributionFileNamesResponse(
                        contributionFileNamesResponseDTO),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContributionFileTransactionResponse>
            getEmployerContributionFileTransactions(String contributionFilePortalId) {
        contributionFilePortalId = StringUtil.sanitize(contributionFilePortalId);
        var errors =
                contributionFileTransactionValidator.validateContributionFileTransaction(
                        contributionFilePortalId);
        exceptionService.handleValidationError(errors);
        var contributionFileTransactionResponseDTO =
                contributionFileService.getEmployerContributionFileTransactions(
                        contributionFilePortalId);
        return new ResponseEntity<>(
                contributionFileTransactionResponseMapper.contributionFileTransactionResponse(
                        contributionFileTransactionResponseDTO),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateContributionFileStatusResponse> updateContributionFileStatus(
            UpdateContributionFileStatusRequest request) {
        Set<Integer> errorCodes =
                contributionFileStatusValidator.validateContributionStatus(request);
        exceptionService.handleValidationError(errorCodes);
        var requestDTO = contributionFileStatusMapper.map(request);
        var responseDTO = contributionFileService.updateContributionFileStatus(requestDTO);
        var response = contributionFileStatusResponseMapper.map(responseDTO);
        log.debug("contribution status updated : {}", response);
        return ResponseEntity.ok(response);
    }
}
