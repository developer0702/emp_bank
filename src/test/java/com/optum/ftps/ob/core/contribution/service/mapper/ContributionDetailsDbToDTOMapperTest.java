package com.optum.ftps.ob.core.contribution.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.constants.ContributionType;
import com.optum.ftps.ob.core.contribution.constants.ContributionTypeCodes;
import com.optum.ftps.ob.core.contribution.data.ContributionDetailsDbDTOData;
import com.optum.ftps.ob.core.contribution.data.StatusMessageTypeData;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionFileDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionInfoDbDTO;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class ContributionDetailsDbToDTOMapperTest {

    @Mock private ServiceMessageTypeRepository serviceMessageTypeRepository;

    @InjectMocks private ContributionDetailsDbToDTOMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertdbToDto() {
        // Arrange
        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertEquals(1, responseDTO.getContributionFiles().size());
        ContributionFileDTO fileDTO = responseDTO.getContributionFiles().get(0);
        assertEquals("file1", fileDTO.getContributionFileId());
        assertEquals("Success", fileDTO.getContributionFileStatus().getStatusDescription());
    }

    @Test
    void testAddEmptyAchContribution() {
        // Arrange
        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setAchContributionId(null);

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.RECORD_NOT_FOUND_ERROR_CODE))
                .thenReturn(statusMessageType);

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertNotNull(responseDTO.getContributionFiles());
    }

    @Test
    void testCheckVontributionTypes_currentyear_participant() {
        // Arrange

        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionYear(ContributionType.CURRENT_YEAR.getContributionTypeName());
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionType(ContributionType.PARTICIPANT.getContributionTypeName());

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertEquals(1, responseDTO.getContributionFiles().size());
        ContributionFileDTO fileDTO = responseDTO.getContributionFiles().get(0);
        assertEquals(
                ContributionTypeCodes.TYPE_CODE_001.getContributionTypeCode(),
                fileDTO.getContributionRecords().get(0).getContributionType().getCode());
    }

    @Test
    void testCheckVontributionTypes_previousyear_participant() {
        // Arrange

        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionYear(ContributionType.PREVIOUS_YEAR.getContributionTypeName());
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionType(ContributionType.PARTICIPANT.getContributionTypeName());

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertEquals(1, responseDTO.getContributionFiles().size());
        ContributionFileDTO fileDTO = responseDTO.getContributionFiles().get(0);
        assertEquals(
                ContributionTypeCodes.TYPE_CODE_002.getContributionTypeCode(),
                fileDTO.getContributionRecords().get(0).getContributionType().getCode());
    }

    @Test
    void testCheckContributionTypes_previousyear_employer() {
        // Arrange

        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionYear(ContributionType.PREVIOUS_YEAR.getContributionTypeName());
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionType(ContributionType.EMPLOYER.getContributionTypeName());

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertEquals(1, responseDTO.getContributionFiles().size());
        ContributionFileDTO fileDTO = responseDTO.getContributionFiles().get(0);
        assertEquals(
                ContributionTypeCodes.TYPE_CODE_004.getContributionTypeCode(),
                fileDTO.getContributionRecords().get(0).getContributionType().getCode());
    }

    @Test
    void testCheckContributionTypes_empty() {
        // Arrange

        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionYear(ContributionInfoConstants.EMPTY_STRING);
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionType(
                        ContributionType.ROLLOVER_CONTRIBUTION.getContributionTypeName());

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertEquals(1, responseDTO.getContributionFiles().size());
        ContributionFileDTO fileDTO = responseDTO.getContributionFiles().get(0);
        assertEquals(
                ContributionTypeCodes.TYPE_CODE_005.getContributionTypeCode(),
                fileDTO.getContributionRecords().get(0).getContributionType().getCode());
    }

    @Test
    void testCheckContributionTypes_empty_invalid() {
        // Arrange

        List<ContributionInfoDbDTO> contributionInfoDbDTOList = getcontributionInfoDbDTOList();
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionYear(ContributionInfoConstants.EMPTY_STRING);
        contributionInfoDbDTOList
                .get(0)
                .getContributionDetailsDbDTOList()
                .get(0)
                .setContributionType(
                        ContributionType.INVALID_CONTRIBUTION.getContributionTypeName());

        StatusMessageType statusMessageType = StatusMessageTypeData.getStatusMessageType();

        when(serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED))
                .thenReturn(statusMessageType);

        // Act
        ContributionDetailsResponseDTO responseDTO =
                mapper.convertdbToDto(contributionInfoDbDTOList, false);

        // Assert
        assertEquals(1, responseDTO.getContributionFiles().size());
        ContributionFileDTO fileDTO = responseDTO.getContributionFiles().get(0);
        assertEquals(
                ContributionTypeCodes.TYPE_CODE_006.getContributionTypeCode(),
                fileDTO.getContributionRecords().get(0).getContributionType().getCode());
    }

    private List<ContributionInfoDbDTO> getcontributionInfoDbDTOList() {
        List<ContributionInfoDbDTO> contributionInfoDbDTOList = new ArrayList<>();
        ContributionInfoDbDTO contributionInfoDbDTO = new ContributionInfoDbDTO();
        List<ContributionDetailsDbDTO> contributionDetailsDbDTOList = new ArrayList<>();
        ContributionDetailsDbDTO contributionDetailsDbDTO =
                ContributionDetailsDbDTOData.getContributionDetailDbDTO();
        contributionDetailsDbDTOList.add(contributionDetailsDbDTO);
        contributionInfoDbDTO.setContributionDetailsDbDTOList(contributionDetailsDbDTOList);
        contributionInfoDbDTOList.add(contributionInfoDbDTO);
        return contributionInfoDbDTOList;
    }
}
