package com.optum.ftps.ob.core.contribution.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.data.ContributionSummaryDTOData;
import com.optum.ftps.ob.core.contribution.data.StatusMessageTypeData;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionFileSummaryDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class ContributionSummaryDbToDTOMapperTest {

    @Mock private ServiceMessageTypeRepository serviceMessageTypeRepository;

    @InjectMocks private ContributionSummaryDbToDTOMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapContributionSummaryDbToDTO() {
        // Arrange
        List<ContributionFileSummaryDTO> contributionFileSummaryDb = new ArrayList<>();
        ContributionFileSummaryDTO summaryDTO =
                ContributionSummaryDTOData.getContributionSummaryDTOData();
        contributionFileSummaryDb.add(summaryDTO);

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionSummaryResponseDTO responseDTO =
                mapper.mapContributionSummaryDbToDTO(contributionFileSummaryDb, "request123");

        // Assert
        assertEquals("request123", responseDTO.getRequestId());
        assertEquals(1, responseDTO.getContributionFileSummary().size());

        StatusDTO statusDTO = responseDTO.getStatus();
        assertEquals("Info", statusDTO.getSeverity());
    }
}
