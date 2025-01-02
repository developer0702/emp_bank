package com.optum.ftps.ob.core.contribution.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

class ContributionInfoControllerTest {

    @Mock private ExceptionService exceptionService;
    @Mock private ContributionInfoService contributionInfoService;
    @Mock private ContributionSummaryValidator contributionSummaryValidator;
    @Mock private ContributionDetailsValidator contributionDetailsValidator;
    @Mock private ContributionDetailRequestMapper contributionDetailRequestMapper;
    @Mock private ContributionSummaryRequestMapper contributionSummaryRequestMapper;
    @Mock private ContributionDetailsResponseMapper contributionDetailsResponseMapper;
    @Mock private ContributionSummaryResponseMapper contributionSummaryResponseMapper;

    @InjectMocks private ContributionInfoController contributionInfoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContributionDetails() {
        // Arrange
        ContributionDetailsRequest request = new ContributionDetailsRequest();
        ContributionDetailsRequestDTO requestDTO = new ContributionDetailsRequestDTO();
        ContributionDetailsResponseDTO responseDTO = new ContributionDetailsResponseDTO();
        ContributionDetailsResponse response = new ContributionDetailsResponse();

        when(contributionDetailsValidator.validateContributionDetails(request))
                .thenReturn(Collections.emptyList());
        when(contributionDetailRequestMapper.map(request)).thenReturn(requestDTO);
        when(contributionInfoService.contributionDetails(requestDTO)).thenReturn(responseDTO);
        when(contributionDetailsResponseMapper.contrbDetailsResponse(responseDTO))
                .thenReturn(response);

        // Act
        ResponseEntity<ContributionDetailsResponse> result =
                contributionInfoController.getContributionDetails(request);

        // Assert
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetContributionSummary() {
        // Arrange
        ContributionSummaryRequest request = new ContributionSummaryRequest();
        ContributionSummaryRequestDTO requestDTO = new ContributionSummaryRequestDTO();
        ContributionSummaryResponseDTO responseDTO = new ContributionSummaryResponseDTO();
        ContributionSummaryResponse response = new ContributionSummaryResponse();

        when(contributionSummaryValidator.validateContributionSummary(request))
                .thenReturn(Collections.emptyList());
        when(contributionSummaryRequestMapper.contrbSummaryDTO(request)).thenReturn(requestDTO);
        when(contributionInfoService.contributionSummary(requestDTO)).thenReturn(responseDTO);
        when(contributionSummaryResponseMapper.contrbSummaryResponse(responseDTO))
                .thenReturn(response);

        // Act
        ResponseEntity<ContributionSummaryResponse> result =
                contributionInfoController.getContributionSummary(request);

        // Assert
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetContributionDetails_WithErrors() {
        // Arrange
        ContributionDetailsRequest request = new ContributionDetailsRequest();
        ErrorItem errorItem = ErrorItem.builder().build();
        List<Integer> errors = Collections.singletonList(1);

        when(contributionDetailsValidator.validateContributionDetails(request)).thenReturn(errors);
        when(exceptionService.getError(anyInt())).thenReturn(errorItem);

        // Act & Assert
        try {
            contributionInfoController.getContributionDetails(request);
        } catch (ValidationException e) {
            assertEquals(1, e.getErrors().size());
        }
    }

    @Test
    void testGetContributionSummary_WithErrors() {
        // Arrange
        ContributionSummaryRequest request = new ContributionSummaryRequest();
        List<Integer> errors = Collections.singletonList(1);
        ErrorItem errorItem = ErrorItem.builder().build();

        when(contributionSummaryValidator.validateContributionSummary(request)).thenReturn(errors);
        when(exceptionService.getError(anyInt())).thenReturn(errorItem);

        // Act & Assert
        try {
            contributionInfoController.getContributionSummary(request);
        } catch (ValidationException e) {
            assertEquals(1, e.getErrors().size());
        }
    }
}
