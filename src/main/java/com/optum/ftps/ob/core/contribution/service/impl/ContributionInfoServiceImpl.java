package com.optum.ftps.ob.core.contribution.service.impl;

import static com.optum.ftps.ob.core.contribution.util.StringUtil.getTrimmedString;
import static com.optum.ftps.ob.core.contribution.util.StringUtil.getTrimmedStringUpperCase;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.constants.FileDetailsRequestType;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionInfoDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.FileDetailsDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.InvalidReasonDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContrbutionFileInfoDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionFileSummaryDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryRequestDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;
import com.optum.ftps.ob.core.contribution.exceptions.NotFoundException;
import com.optum.ftps.ob.core.contribution.repository.ContributionInfoRepository;
import com.optum.ftps.ob.core.contribution.service.ContributionInfoService;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionDetailsDbToDTOMapper;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionSummaryDbToDTOMapper;
import com.optum.ftps.ob.core.contribution.util.DateUtil;
import com.optum.ftps.ob.core.contribution.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContributionInfoServiceImpl implements ContributionInfoService {

    @Value("${max.contribution.detail.file.req}")
    private Integer maxContributionFileDetailsRequest;

    private final ExceptionService exceptionService;
    private final ContributionInfoRepository contributionInfoRepository;
    private final ContributionDetailsDbToDTOMapper contributionDetailsDbToDTOMapper;
    private final ContributionSummaryDbToDTOMapper contributionSummaryDbToDTOMapper;

    @Override
    public ContributionDetailsResponseDTO contributionDetails(
            ContributionDetailsRequestDTO contributionDetailsRequestDTO) {
        log.debug("Getting contributionDetails : {}", contributionDetailsRequestDTO);

        List<ContributionDetailsDbDTO> contributionDbRecords;
        List<ContributionInfoDbDTO> contributionInfoDbDTOS = new ArrayList<>();
        try {
            for (var fileDetailsDTO : contributionDetailsRequestDTO.getFileDetails()) {
                var contributionInfoDbDTO = new ContributionInfoDbDTO();
                if (fileDetailsDTO
                        .requestType()
                        .equals(FileDetailsRequestType.VALID_REQ.getRequestType())) {
                    contributionDbRecords =
                            contributionInfoRepository.getValidContributionRecords(
                                    fileDetailsDTO.contributionFileId());
                    var contributionDtlRecList =
                            getValidContributionRecords(
                                    contributionDbRecords, fileDetailsDTO.contributionFileId());
                    contributionInfoDbDTO.setContributionDetailsDbDTOList(contributionDtlRecList);
                    log.debug("VALID_REQ contributionDtlRecList : {}", contributionDtlRecList);
                } else if (fileDetailsDTO
                        .requestType()
                        .equals(FileDetailsRequestType.INVALID_REQ.getRequestType())) {
                    contributionDbRecords =
                            contributionInfoRepository.getInvalidContributionRecords(
                                    fileDetailsDTO.contributionFileId());
                    var contributionDtlRecList =
                            getInvalidContributionRecords(
                                    contributionDbRecords, fileDetailsDTO.contributionFileId());
                    contributionInfoDbDTO.setContributionDetailsDbDTOList(contributionDtlRecList);
                    log.debug("INVALID_REQ contributionDtlRecList : {}", contributionDtlRecList);
                } else {
                    contributionDbRecords =
                            contributionInfoRepository.getAllContributionRecords(
                                    fileDetailsDTO.contributionFileId());
                    var contributionDtlRecList =
                            getInvalidContributionRecords(
                                    contributionDbRecords, fileDetailsDTO.contributionFileId());
                    contributionInfoDbDTO.setContributionDetailsDbDTOList(contributionDtlRecList);
                    log.debug("ALL_REQ contributionDtlRecList : {}", contributionDtlRecList);
                }
                contributionInfoDbDTOS.add(contributionInfoDbDTO);
            }

            return contributionDetailsDbToDTOMapper.convertdbToDto(
                    contributionInfoDbDTOS,
                    isTrimmedValue(
                            contributionDetailsRequestDTO, maxContributionFileDetailsRequest));

        } catch (Exception e) {
            log.debug("Error in contributionDetails : {}", e.getMessage());
            var errorCode = ContributionInfoConstants.RECORD_NOT_FOUND_ERROR_CODE;
            var errorItem = exceptionService.getError(errorCode);
            throw new NotFoundException(List.of(errorItem));
        }
    }

    @Override
    public ContributionSummaryResponseDTO contributionSummary(
            ContributionSummaryRequestDTO contributionSummaryRequestDTO) {
        log.debug("contributionSummary request : {}", contributionSummaryRequestDTO);

        List<ContributionSummaryDbDTO> contributionSummaryDbDTOList = null;
        if ("Y".equalsIgnoreCase(contributionSummaryRequestDTO.getRestrictToFunded())) {
            // If the date from and date to request parameters exists
            var currentDate = DateUtil.getCurrentDate();
            if (contributionSummaryRequestDTO.getDateFrom() != null
                    && contributionSummaryRequestDTO.getDateTo() != null) {
                // Gets the funded files for the given date range, based on file effective date
                var dateTo =
                        DateUtil.parse(
                                contributionSummaryRequestDTO.getDateTo(),
                                ContributionInfoConstants.DATE_FORMAT);
                if (currentDate.before(dateTo)) {
                    dateTo = currentDate;
                }

                contributionSummaryDbDTOList =
                        contributionInfoRepository.getFundYDateRangeMaxFiles(
                                contributionSummaryRequestDTO.getEmployerGroupId(),
                                contributionSummaryRequestDTO.getMaxNumberOfFiles(),
                                DateUtil.parse(
                                        contributionSummaryRequestDTO.getDateFrom(),
                                        ContributionInfoConstants.DATE_FORMAT),
                                dateTo);
                log.debug(
                        "contributionSummary contributionSummaryDbDTOList : {}",
                        contributionSummaryDbDTOList);
            } else {
                // Gets the funded files restricting to max number of files based on request
                // parameter
                contributionSummaryDbDTOList =
                        contributionInfoRepository.getFundYMaxFiles(
                                contributionSummaryRequestDTO.getEmployerGroupId(),
                                contributionSummaryRequestDTO.getMaxNumberOfFiles(),
                                currentDate);
                log.debug(
                        "contributionSummary getFundYMaxFiles : {}", contributionSummaryDbDTOList);
            }
        } else {
            // If the date from and date to request parameters exists
            if (contributionSummaryRequestDTO.getDateFrom() != null
                    && contributionSummaryRequestDTO.getDateTo() != null) {
                // Gets all files (funded, non-funded) for the given date range, based on file
                // submitted date
                contributionSummaryDbDTOList =
                        contributionInfoRepository.getFundNDateRangeMaxFiles(
                                contributionSummaryRequestDTO.getEmployerGroupId(),
                                contributionSummaryRequestDTO.getMaxNumberOfFiles(),
                                DateUtil.parse(
                                        contributionSummaryRequestDTO.getDateFrom(),
                                        ContributionInfoConstants.DATE_FORMAT),
                                DateUtil.parse(
                                        contributionSummaryRequestDTO.getDateTo(),
                                        ContributionInfoConstants.DATE_FORMAT));
                log.debug(
                        "contributionSummary getFundNDateRangeMaxFiles : {}",
                        contributionSummaryDbDTOList);
            } else {
                // Gets all files (funded, non-funded) restricting to max number of files based on
                // request parameter
                contributionSummaryDbDTOList =
                        contributionInfoRepository.getFundNMaxFiles(
                                contributionSummaryRequestDTO.getEmployerGroupId(),
                                contributionSummaryRequestDTO.getMaxNumberOfFiles());
                log.debug(
                        "contributionSummary getFundNMaxFiles : {}", contributionSummaryDbDTOList);
            }
        }
        var contributionFileSummaryDTOList =
                buildContributionSummaryData(contributionSummaryDbDTOList);
        return contributionSummaryDbToDTOMapper.mapContributionSummaryDbToDTO(
                contributionFileSummaryDTOList, contributionSummaryRequestDTO.getRequestId());
    }

    private List<ContributionFileSummaryDTO> buildContributionSummaryData(
            List<ContributionSummaryDbDTO> contributionSummaryDbDTOList) {
        log.debug("Building contribution summary data");
        List<ContributionFileSummaryDTO> contributionFileSummaryList = new ArrayList<>();
        for (ContributionSummaryDbDTO contributionSummaryDbDTO : contributionSummaryDbDTOList) {
            ContributionFileSummaryDTO contributionFileSummaryDTO =
                    new ContributionFileSummaryDTO();
            var statusDTO = new StatusDTO();
            var contrbutionFileInfoDTO = new ContrbutionFileInfoDTO();

            statusDTO.setStatusCode(contributionSummaryDbDTO.getServiceReasonCode());
            statusDTO.setSeverity(contributionSummaryDbDTO.getServiceMessageSeverityCode());
            statusDTO.setStatusDescription(contributionSummaryDbDTO.getServiceMessageText());

            contributionFileSummaryDTO.setFileStatus(statusDTO);

            contrbutionFileInfoDTO.setContributionFileKey(
                    contributionSummaryDbDTO.getContributionFilePrtlId());
            contrbutionFileInfoDTO.setEmployerGroupNumber(
                    contributionSummaryDbDTO.getCustomerPolicyNumber());
            var date =
                    DateUtil.parse(
                            contributionSummaryDbDTO.getSubmittedDate(),
                            ContributionInfoConstants.DATE_FORMAT);
            contrbutionFileInfoDTO.setSubmittedDate(
                    DateUtil.format(date, ContributionInfoConstants.DATE_FORMAT));
            contrbutionFileInfoDTO.setEffectiveDate(contributionSummaryDbDTO.getEffectiveDate());
            contrbutionFileInfoDTO.setValidRecordCount(
                    BigDecimal.valueOf(contributionSummaryDbDTO.getValidRecordCount()));
            contrbutionFileInfoDTO.setTotalValidContributionAmount(
                    String.valueOf(contributionSummaryDbDTO.getTotalValidContributionAmount()));
            contrbutionFileInfoDTO.setInvalidRecordCount(
                    BigDecimal.valueOf(contributionSummaryDbDTO.getInvalidRecordCount()));
            contrbutionFileInfoDTO.setInvalidTotalValidContributionAmount(
                    BigDecimal.valueOf(
                            contributionSummaryDbDTO.getInvalidTotalValidContributionAmount()));
            contrbutionFileInfoDTO.setFundedIndicator(
                    DateUtil.parse(
                                            contributionSummaryDbDTO.getEffectiveDate(),
                                            ContributionInfoConstants.DATE_FORMAT)
                                    .before(DateUtil.getCurrentDate())
                            ? "Y"
                            : "N");

            contributionFileSummaryDTO.setContributionFileInfo(contrbutionFileInfoDTO);
            contributionFileSummaryList.add(contributionFileSummaryDTO);
            log.debug("buildContributionSummaryData : {}", contributionFileSummaryList);
        }

        if (contributionFileSummaryList.isEmpty()) {
            log.debug("No records found for contribution summary");
            handleError();
        }

        return contributionFileSummaryList;
    }

    private List<ContributionDetailsDbDTO> getValidContributionRecords(
            List<ContributionDetailsDbDTO> contributionDetailsDbDTOList,
            String requestContributionFileID) {
        ContributionDetailsDbDTO contributionDetailsDbDTO;
        var contributionDtlRecList = buildValidContributionRecords(contributionDetailsDbDTOList);
        if (contributionDtlRecList.isEmpty()) {
            contributionDetailsDbDTO =
                    ContributionDetailsDbDTO.builder()
                            .contributionFilePrtId(requestContributionFileID)
                            .build();
            contributionDetailsDbDTOList.add(contributionDetailsDbDTO);
        }
        log.debug("getValidContributionRecords : {}", contributionDetailsDbDTOList);
        return contributionDetailsDbDTOList;
    }

    private List<ContributionDetailsDbDTO> getInvalidContributionRecords(
            List<ContributionDetailsDbDTO> contributionDetailsDbDTOList,
            String requestContributionFileID) {
        ContributionDetailsDbDTO contributionDetailsDbDTO;
        var contributionDtlRecList = buildInvalidContributionRecords(contributionDetailsDbDTOList);
        if (contributionDtlRecList.isEmpty()) {
            contributionDetailsDbDTO =
                    ContributionDetailsDbDTO.builder()
                            .contributionFilePrtId(requestContributionFileID)
                            .build();
            contributionDetailsDbDTOList.add(contributionDetailsDbDTO);
        }
        log.debug("getInvalidContributionRecords : {}", contributionDetailsDbDTOList);
        return contributionDetailsDbDTOList;
    }

    List<ContributionDetailsDbDTO> buildValidContributionRecords(
            List<ContributionDetailsDbDTO> contributionDetailsDbDTOList) {
        List<ContributionDetailsDbDTO> detailsDbDTOList = new ArrayList<>();
        String previousContributionFileID = null;
        String contributionFileID = null;
        for (ContributionDetailsDbDTO dbDTO : contributionDetailsDbDTOList) {
            contributionFileID = dbDTO.getAchContributionId();
            if (!contributionFileID.equalsIgnoreCase(previousContributionFileID)) {
                var contributionDetailsDbDTO = normalize(dbDTO);
                detailsDbDTOList.add(contributionDetailsDbDTO);
            }
            previousContributionFileID = contributionFileID;
        }
        log.debug("buildValidContributionRecords : {}", detailsDbDTOList);
        return detailsDbDTOList;
    }

    List<ContributionDetailsDbDTO> buildInvalidContributionRecords(
            List<ContributionDetailsDbDTO> contributionDetailsDbDTOList) {
        List<ContributionDetailsDbDTO> detailsDbDTOList = new ArrayList<>();
        List<InvalidReasonDTO> invalidReasonList = null;
        String previousContributionFileID = null;
        String contributionFileID = null;
        for (ContributionDetailsDbDTO dbDTO : contributionDetailsDbDTOList) {
            contributionFileID = dbDTO.getAchContributionId();
            if (!contributionFileID.equalsIgnoreCase(previousContributionFileID)) {
                if (!StringUtil.isEmpty(dbDTO.getErrorCode().trim())) {
                    var invalidReasonDTO =
                            buildInvalidReason(dbDTO.getErrorCode(), dbDTO.getErrorText());
                    invalidReasonList = new ArrayList<>();
                    invalidReasonList.add(invalidReasonDTO);
                }
                var contributionDetailsDbDTO = normalize(dbDTO);
                contributionDetailsDbDTO.setInvalidReasonList(invalidReasonList);
                detailsDbDTOList.add(contributionDetailsDbDTO);
            } else {
                if (!StringUtil.isEmpty(dbDTO.getErrorCode().trim())) {
                    var invalidReasonDTO =
                            buildInvalidReason(dbDTO.getErrorCode(), dbDTO.getErrorText());
                    invalidReasonList.add(invalidReasonDTO);
                }
            }

            previousContributionFileID = contributionFileID;
        }
        log.debug("buildInvalidContributionRecords : {}", detailsDbDTOList);
        return detailsDbDTOList;
    }

    InvalidReasonDTO buildInvalidReason(String errorCode, String errorText) {
        var invalidReasonDTO = new InvalidReasonDTO();
        invalidReasonDTO.setCode(errorCode.trim());
        invalidReasonDTO.setCodeName(errorText.trim());
        log.debug("buildInvalidReason : {}", invalidReasonDTO);
        return invalidReasonDTO;
    }

    boolean isTrimmedValue(
            ContributionDetailsRequestDTO contributionDetailsRequestDTO, int reqFileCount) {
        var fileDetailsDTOList = contributionDetailsRequestDTO.getFileDetails();
        if (fileDetailsDTOList != null && fileDetailsDTOList.size() > reqFileCount) {
            List<FileDetailsDTO> trimmedRequests = new ArrayList<>();
            for (int i = 0; i < reqFileCount; i++) {
                trimmedRequests.add(fileDetailsDTOList.get(i));
            }
            contributionDetailsRequestDTO.setFileDetails(trimmedRequests);
            return true;
        }
        return false;
    }

    ContributionDetailsDbDTO normalize(ContributionDetailsDbDTO dbDTO) {
        return ContributionDetailsDbDTO.builder()
                .contributionFilePrtId(getTrimmedString(dbDTO.getContributionFilePrtId()))
                .accountHolderSSN(dbDTO.getAccountHolderSSN())
                .accountHolderFirstNm(getTrimmedString(dbDTO.getAccountHolderFirstNm()))
                .accountHolderMilddleNm(getTrimmedString(dbDTO.getAccountHolderMilddleNm()))
                .accountHolderLastNm(getTrimmedString(dbDTO.getAccountHolderLastNm()))
                .contributionAmount(dbDTO.getContributionAmount())
                .contributionType(getTrimmedStringUpperCase(dbDTO.getContributionType()))
                .validIndicator(getTrimmedString(dbDTO.getValidIndicator()))
                .contributionYear(getTrimmedStringUpperCase(dbDTO.getContributionYear()))
                .build();
    }

    void handleError() {
        var errorCode = ContributionInfoConstants.RECORD_NOT_FOUND_ERROR_CODE;
        var errorItem = exceptionService.getError(errorCode);
        throw new NotFoundException(List.of(errorItem));
    }
}
