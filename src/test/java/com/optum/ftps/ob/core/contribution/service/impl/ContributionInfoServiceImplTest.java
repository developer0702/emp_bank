package com.optum.ftps.ob.core.contribution.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.constants.FileDetailsRequestType;
import com.optum.ftps.ob.core.contribution.data.ContributionFileDTOData;
import com.optum.ftps.ob.core.contribution.data.ContributionSummaryDbDTOData;
import com.optum.ftps.ob.core.contribution.data.FileDetailsDTOData;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.FileDetailsDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.InvalidReasonDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;
import com.optum.ftps.ob.core.contribution.exceptions.NotFoundException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.contribution.repository.ContributionInfoRepository;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionDetailsDbToDTOMapper;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionSummaryDbToDTOMapper;
import com.optum.ftps.ob.core.contribution.util.DateUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionInfoServiceImplTest {

    @Mock private ExceptionService exceptionService;

    @Mock private ContributionInfoRepository contributionInfoRepository;

    @Mock private ContributionDetailsDbToDTOMapper contributionDetailsDbToDTOMapper;

    @Mock private ContributionSummaryDbToDTOMapper contributionSummaryDbToDTOMapper;

    @InjectMocks @Spy private ContributionInfoServiceImpl contributionInfoService;

    private static final int MAX_CONTRIBUTION_FILE_DETAILS_REQUEST = 2;

    @BeforeEach
    void setUp() {
        contributionInfoService =
                new ContributionInfoServiceImpl(
                        exceptionService,
                        contributionInfoRepository,
                        contributionDetailsDbToDTOMapper,
                        contributionSummaryDbToDTOMapper);
        ReflectionTestUtils.setField(
                contributionInfoService,
                "maxContributionFileDetailsRequest",
                MAX_CONTRIBUTION_FILE_DETAILS_REQUEST);
    }

    @Test
    void testContributionDetails_ValidInput() {
        // Arrange
        ContributionDetailsRequestDTO requestDTO = new ContributionDetailsRequestDTO();
        requestDTO.getFileDetails().add(FileDetailsDTOData.getFileDetails());
        ContributionDetailsResponseDTO expectedResponse = new ContributionDetailsResponseDTO();
        expectedResponse.setContributionFiles(
                List.of(ContributionFileDTOData.getContributionFileDTO()));
        Mockito.doReturn(expectedResponse)
                .when(contributionDetailsDbToDTOMapper)
                .convertdbToDto(any(), any(Boolean.class));

        // Act
        ContributionDetailsResponseDTO response =
                contributionInfoService.contributionDetails(requestDTO);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    void testContributionDetails_InvalidInput() {
        var requestDTO = new ContributionDetailsRequestDTO();
        var fileDetailsDTO =
                new FileDetailsDTO("file1", FileDetailsRequestType.INVALID_REQ.getRequestType());
        var expectedResponse = new ContributionDetailsResponseDTO();

        requestDTO.getFileDetails().add(fileDetailsDTO);
        expectedResponse.setContributionFiles(
                List.of(ContributionFileDTOData.getContributionFileDTO()));
        Mockito.doReturn(expectedResponse)
                .when(contributionDetailsDbToDTOMapper)
                .convertdbToDto(any(), any(Boolean.class));

        var response = contributionInfoService.contributionDetails(requestDTO);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testContributionDetails_AllInput() {
        var requestDTO = new ContributionDetailsRequestDTO();
        var fileDetailsDTO =
                new FileDetailsDTO("file1", FileDetailsRequestType.ALL_REQ.getRequestType());
        var expectedResponse = new ContributionDetailsResponseDTO();

        requestDTO.getFileDetails().add(fileDetailsDTO);
        expectedResponse.setContributionFiles(
                List.of(ContributionFileDTOData.getContributionFileDTO()));
        Mockito.doReturn(expectedResponse)
                .when(contributionDetailsDbToDTOMapper)
                .convertdbToDto(any(), any(Boolean.class));

        var response = contributionInfoService.contributionDetails(requestDTO);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testContributionDetails_Exception() {
        var requestDTO = new ContributionDetailsRequestDTO();
        var fileDetailsDTO =
                new FileDetailsDTO("file1", FileDetailsRequestType.ALL_REQ.getRequestType());
        var expectedResponse = new ContributionDetailsResponseDTO();

        requestDTO.getFileDetails().add(fileDetailsDTO);
        expectedResponse.setContributionFiles(
                List.of(ContributionFileDTOData.getContributionFileDTO()));
        Mockito.doThrow(NullPointerException.class)
                .when(contributionDetailsDbToDTOMapper)
                .convertdbToDto(any(), any(Boolean.class));

        Assertions.assertThrows(
                NullPointerException.class,
                () -> contributionInfoService.contributionDetails(requestDTO));
    }

    @Test
    void testContributionSummary_NotFoundException() {
        // Arrange
        ContributionSummaryRequestDTO requestDTO = new ContributionSummaryRequestDTO();
        requestDTO.setEmployerGroupId("group1");
        requestDTO.setRestrictToFunded("Y");
        requestDTO.setMaxNumberOfFiles(10);
        requestDTO.setRequestId("12345");

        Mockito.doThrow(NullPointerException.class)
                .when(contributionInfoRepository)
                .getFundYMaxFiles(any(), any(), any());

        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> contributionInfoService.contributionSummary(requestDTO));
    }

    @Test
    void testContributionSummary_ValidInput_FundedDateRange() {
        // Arrange
        ContributionSummaryRequestDTO requestDTO = getContributionSummaryRequestDTO();

        List<ContributionSummaryDbDTO> dbDTOList =
                List.of(ContributionSummaryDbDTOData.getContributionSummaryDbDTO());
        when(contributionInfoRepository.getFundYDateRangeMaxFiles(
                        "group1",
                        10,
                        DateUtil.parse("2023-01-01", "yyyy-MM-dd"),
                        DateUtil.parse("2023-12-31", "yyyy-MM-dd")))
                .thenReturn(dbDTOList);

        ContributionSummaryResponseDTO expectedResponse = new ContributionSummaryResponseDTO();
        Mockito.doReturn(expectedResponse)
                .when(contributionSummaryDbToDTOMapper)
                .mapContributionSummaryDbToDTO(any(), any());

        // Act
        ContributionSummaryResponseDTO response =
                contributionInfoService.contributionSummary(requestDTO);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    void testContributionSummary_ValidInput_Funded() {
        // Arrange
        ContributionSummaryRequestDTO requestDTO = getContributionSummaryRequestDTO();
        requestDTO.setDateFrom(null);
        requestDTO.setDateTo(null);

        List<ContributionSummaryDbDTO> dbDTOList =
                List.of(ContributionSummaryDbDTOData.getContributionSummaryDbDTO());
        Mockito.doReturn(dbDTOList)
                .when(contributionInfoRepository)
                .getFundYMaxFiles(any(), any(), any());

        ContributionSummaryResponseDTO expectedResponse = new ContributionSummaryResponseDTO();
        Mockito.doReturn(expectedResponse)
                .when(contributionSummaryDbToDTOMapper)
                .mapContributionSummaryDbToDTO(any(), any());

        // Act
        ContributionSummaryResponseDTO response =
                contributionInfoService.contributionSummary(requestDTO);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    void testContributionSummary_ValidInput_NotFundedDateRange() {
        // Arrange
        ContributionSummaryRequestDTO requestDTO = getContributionSummaryRequestDTO();
        requestDTO.setRestrictToFunded("N");

        List<ContributionSummaryDbDTO> dbDTOList =
                List.of(ContributionSummaryDbDTOData.getContributionSummaryDbDTO());
        when(contributionInfoRepository.getFundNDateRangeMaxFiles(
                        "group1",
                        10,
                        DateUtil.parse("2023-01-01", "yyyy-MM-dd"),
                        DateUtil.parse("2023-12-31", "yyyy-MM-dd")))
                .thenReturn(dbDTOList);

        ContributionSummaryResponseDTO expectedResponse = new ContributionSummaryResponseDTO();
        Mockito.doReturn(expectedResponse)
                .when(contributionSummaryDbToDTOMapper)
                .mapContributionSummaryDbToDTO(any(), any());

        // Act
        ContributionSummaryResponseDTO response =
                contributionInfoService.contributionSummary(requestDTO);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    void testContributionSummary_ValidInput_NotFunded() {
        // Arrange
        ContributionSummaryRequestDTO requestDTO = getContributionSummaryRequestDTO();
        requestDTO.setDateFrom(null);
        requestDTO.setDateTo(null);
        requestDTO.setRestrictToFunded("N");

        List<ContributionSummaryDbDTO> dbDTOList =
                List.of(ContributionSummaryDbDTOData.getContributionSummaryDbDTO());
        Mockito.doReturn(dbDTOList).when(contributionInfoRepository).getFundNMaxFiles(any(), any());

        ContributionSummaryResponseDTO expectedResponse = new ContributionSummaryResponseDTO();
        Mockito.doReturn(expectedResponse)
                .when(contributionSummaryDbToDTOMapper)
                .mapContributionSummaryDbToDTO(any(), any());

        // Act
        ContributionSummaryResponseDTO response =
                contributionInfoService.contributionSummary(requestDTO);

        // Assert
        assertEquals(expectedResponse, response);
    }

    private ContributionSummaryRequestDTO getContributionSummaryRequestDTO() {
        ContributionSummaryRequestDTO requestDTO = new ContributionSummaryRequestDTO();
        requestDTO.setEmployerGroupId("group1");
        requestDTO.setRestrictToFunded("Y");
        requestDTO.setDateFrom("2023-01-01");
        requestDTO.setDateTo("2023-12-31");
        requestDTO.setMaxNumberOfFiles(10);
        requestDTO.setRequestId("12345");
        return requestDTO;
    }

    @Test
    void testNormalize() {
        // Arrange
        ContributionDetailsDbDTO input =
                ContributionDetailsDbDTO.builder()
                        .contributionFilePrtId(" 12345 ")
                        .accountHolderSSN("123-45-6789")
                        .accountHolderFirstNm(" John ")
                        .accountHolderMilddleNm(" A. ")
                        .accountHolderLastNm(" Doe ")
                        .contributionAmount(Double.parseDouble("100.00"))
                        .contributionType(" type ")
                        .validIndicator(" Y ")
                        .contributionYear(" 2023 ")
                        .build();

        ContributionDetailsDbDTO expected =
                ContributionDetailsDbDTO.builder()
                        .contributionFilePrtId("12345")
                        .accountHolderSSN("123-45-6789")
                        .accountHolderFirstNm("John")
                        .accountHolderMilddleNm("A.")
                        .accountHolderLastNm("Doe")
                        .contributionAmount(Double.parseDouble("100.00"))
                        .contributionType("TYPE")
                        .validIndicator("Y")
                        .contributionYear("2023")
                        .build();

        // Act
        ContributionDetailsDbDTO result = contributionInfoService.normalize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testIsTrimmedValue_ExceedsMaxRequest() {
        // Arrange
        ContributionDetailsRequestDTO requestDTO = new ContributionDetailsRequestDTO();
        List<FileDetailsDTO> fileDetailsList = new ArrayList<>();
        for (int i = 0; i < MAX_CONTRIBUTION_FILE_DETAILS_REQUEST + 1; i++) {
            fileDetailsList.add(new FileDetailsDTO("foo", "bar"));
        }
        requestDTO.setFileDetails(fileDetailsList);

        // Act
        boolean result =
                contributionInfoService.isTrimmedValue(
                        requestDTO, MAX_CONTRIBUTION_FILE_DETAILS_REQUEST);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsTrimmedValue_DoesNotExceedMaxRequest() {
        // Arrange
        ContributionDetailsRequestDTO requestDTO = new ContributionDetailsRequestDTO();
        List<FileDetailsDTO> fileDetailsList = new ArrayList<>();
        for (int i = 0; i < MAX_CONTRIBUTION_FILE_DETAILS_REQUEST; i++) {
            fileDetailsList.add(new FileDetailsDTO("foo", "bar"));
        }
        requestDTO.setFileDetails(fileDetailsList);

        // Act
        boolean result =
                contributionInfoService.isTrimmedValue(
                        requestDTO, MAX_CONTRIBUTION_FILE_DETAILS_REQUEST);

        // Assert
        assertFalse(result);
    }

    @Test
    void testBuildInvalidReason() {
        // Arrange
        String errorCode = " ERR001 ";
        String errorText = " Error Text ";

        InvalidReasonDTO expected = new InvalidReasonDTO();
        expected.setCode("ERR001");
        expected.setCodeName("Error Text");

        // Act
        InvalidReasonDTO result = contributionInfoService.buildInvalidReason(errorCode, errorText);

        // Assert
        assertEquals(expected.getCode(), result.getCode());
        assertEquals(expected.getCodeName(), result.getCodeName());
    }

    @Test
    void testBuildInvalidContributionRecords() {
        // Arrange
        ContributionDetailsDbDTO dbDTO1 =
                ContributionDetailsDbDTO.builder()
                        .achContributionId("file1")
                        .errorCode(" ERR001 ")
                        .errorText(" Error Text 1 ")
                        .build();

        ContributionDetailsDbDTO dbDTO2 =
                ContributionDetailsDbDTO.builder()
                        .achContributionId("file1")
                        .errorCode(" ERR002 ")
                        .errorText(" Error Text 2 ")
                        .build();

        List<ContributionDetailsDbDTO> inputList = List.of(dbDTO1, dbDTO2);

        InvalidReasonDTO invalidReason1 = new InvalidReasonDTO();
        invalidReason1.setCode("ERR001");
        invalidReason1.setCodeName("Error Text 1");

        InvalidReasonDTO invalidReason2 = new InvalidReasonDTO();
        invalidReason2.setCode("ERR002");
        invalidReason2.setCodeName("Error Text 2");

        // Act
        List<ContributionDetailsDbDTO> result =
                contributionInfoService.buildInvalidContributionRecords(inputList);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testBuildValidContributionRecords() {
        // Arrange
        ContributionDetailsDbDTO dbDTO1 =
                ContributionDetailsDbDTO.builder()
                        .achContributionId("file1")
                        .contributionFilePrtId(" 12345 ")
                        .accountHolderSSN("123-45-6789")
                        .accountHolderFirstNm(" John ")
                        .accountHolderMilddleNm(" A. ")
                        .accountHolderLastNm(" Doe ")
                        .contributionAmount(Double.parseDouble("100.00"))
                        .contributionType(" type ")
                        .validIndicator(" Y ")
                        .contributionYear(" 2023 ")
                        .build();

        ContributionDetailsDbDTO dbDTO2 =
                ContributionDetailsDbDTO.builder()
                        .achContributionId("file2")
                        .contributionFilePrtId(" 67890 ")
                        .accountHolderSSN("987-65-4321")
                        .accountHolderFirstNm(" Jane ")
                        .accountHolderMilddleNm(" B. ")
                        .accountHolderLastNm(" Smith ")
                        .contributionAmount(Double.parseDouble("200.00"))
                        .contributionType(" type2 ")
                        .validIndicator(" N ")
                        .contributionYear(" 2022 ")
                        .build();

        List<ContributionDetailsDbDTO> inputList = List.of(dbDTO1, dbDTO2);

        ContributionDetailsDbDTO expectedDTO1 =
                ContributionDetailsDbDTO.builder()
                        .achContributionId("file1")
                        .contributionFilePrtId("12345")
                        .accountHolderSSN("123-45-6789")
                        .accountHolderFirstNm("John")
                        .accountHolderMilddleNm("A.")
                        .accountHolderLastNm("Doe")
                        .contributionAmount(Double.parseDouble("100.00"))
                        .contributionType("TYPE")
                        .validIndicator("Y")
                        .contributionYear("2023")
                        .build();

        ContributionDetailsDbDTO expectedDTO2 =
                ContributionDetailsDbDTO.builder()
                        .achContributionId("file2")
                        .contributionFilePrtId("67890")
                        .accountHolderSSN("987-65-4321")
                        .accountHolderFirstNm("Jane")
                        .accountHolderMilddleNm("B.")
                        .accountHolderLastNm("Smith")
                        .contributionAmount(Double.parseDouble("200.00"))
                        .contributionType("TYPE2")
                        .validIndicator("N")
                        .contributionYear("2022")
                        .build();

        List<ContributionDetailsDbDTO> expectedList = List.of(expectedDTO1, expectedDTO2);

        // Act
        List<ContributionDetailsDbDTO> result =
                contributionInfoService.buildValidContributionRecords(inputList);

        // Assert
        assertEquals(expectedList.size(), result.size());
    }

    @Test
    void testHandleError() {
        // Arrange
        ErrorItem errorItem = ErrorItem.builder().build();
        when(exceptionService.getError(any())).thenReturn(errorItem);
        // Act & Assert
        assertThrows(NotFoundException.class, () -> contributionInfoService.handleError());
    }
}
