package com.optum.ftps.ob.core.contribution.controller;

import com.optum.ftps.ob.core.contribution.api.v1.ContributionApi;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.mapper.ContributionDetailRequestMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionDetailsResponseMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionSummaryRequestMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionSummaryResponseMapper;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionDetailsRequest;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionDetailsResponse;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionSummaryRequest;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionSummaryResponse;
import com.optum.ftps.ob.core.contribution.service.ContributionInfoService;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;
import com.optum.ftps.ob.core.contribution.validator.ContributionDetailsValidator;
import com.optum.ftps.ob.core.contribution.validator.ContributionSummaryValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContributionInfoController implements ContributionApi {

    private final ExceptionService exceptionService;
    private final ContributionInfoService contributionInfoService;
    private final ContributionSummaryValidator contributionSummaryValidator;
    private final ContributionDetailsValidator contributionDetailsValidator;
    private final ContributionDetailRequestMapper contributionDetailRequestMapper;
    private final ContributionSummaryRequestMapper contributionSummaryRequestMapper;
    private final ContributionDetailsResponseMapper contributionDetailsResponseMapper;
    private final ContributionSummaryResponseMapper contributionSummaryResponseMapper;

    @Override
    public ResponseEntity<ContributionDetailsResponse> getContributionDetails(
            ContributionDetailsRequest request) {
        log.debug("Received request to get contribution details : {}", request);
        List<Integer> errors = contributionDetailsValidator.validateContributionDetails(request);
        handleErrors(errors);
        var contributionDetailsRequestDTO = contributionDetailRequestMapper.map(request);
        var responseDTO =
                contributionInfoService.contributionDetails(contributionDetailsRequestDTO);
        var response = contributionDetailsResponseMapper.contrbDetailsResponse(responseDTO);
        log.debug("contribution details : {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ContributionSummaryResponse> getContributionSummary(
            ContributionSummaryRequest request) {
        log.debug("Received request to get contribution summary : {}", request);
        List<Integer> errors = contributionSummaryValidator.validateContributionSummary(request);
        handleErrors(errors);

        var requestDTO = contributionSummaryRequestMapper.contrbSummaryDTO(request);
        var responseDTO = contributionInfoService.contributionSummary(requestDTO);
        var response = contributionSummaryResponseMapper.contrbSummaryResponse(responseDTO);
        log.debug("contribution summary : {}", response);
        return ResponseEntity.ok(response);
    }

    private void handleErrors(List<Integer> codes) {
        if (!codes.isEmpty()) {
            log.debug("handling validation codes : {}", codes);
            var errors = codes.stream().map(exceptionService::getError).toList();
            throw new ValidationException(errors);
        }
    }
}
