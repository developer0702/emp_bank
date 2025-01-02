package com.optum.ftps.ob.core.contribution.service.mapper;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.constants.ContributionType;
import com.optum.ftps.ob.core.contribution.constants.ContributionTypeCodes;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionFileDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionInfoDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionRecordDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionTypeDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.PersonNameDTO;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContributionDetailsDbToDTOMapper {

    private final ServiceMessageTypeRepository serviceMessageTypeRepository;

    public ContributionDetailsResponseDTO convertdbToDto(
            List<ContributionInfoDbDTO> contributionInfoDbDTOList, boolean requestTrimmed) {
        var contributionDetailsResponseDTO = new ContributionDetailsResponseDTO();
        List<ContributionFileDTO> contributionFileDTOList = new ArrayList<>();
        if (Objects.nonNull(contributionInfoDbDTOList)) {
            for (ContributionInfoDbDTO contributionInfoDbDTO : contributionInfoDbDTOList) {
                processContribution(contributionInfoDbDTO, contributionFileDTOList);
            }

            var successCode =
                    requestTrimmed
                            ? ContributionInfoConstants.REQUEST_LIMITED_CODE
                            : ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED;
            var statusDTO = getStatus(successCode);

            contributionDetailsResponseDTO.setContrbDetailStatus(statusDTO);
            contributionDetailsResponseDTO.setContributionFiles(contributionFileDTOList);
        }
        return contributionDetailsResponseDTO;
    }

    private void processContribution(
            ContributionInfoDbDTO contributionInfoDbDTO,
            List<ContributionFileDTO> contributionFileDTOList) {
        List<ContributionRecordDTO> contributionRecordList = new ArrayList<>();
        var contributionFileDTO = new ContributionFileDTO();
        if (Objects.nonNull(contributionInfoDbDTO.getContributionDetailsDbDTOList())) {
            String previousContributionID = null;
            String contributionID = null;

            for (ContributionDetailsDbDTO contributionDetailsDbDTO :
                    contributionInfoDbDTO.getContributionDetailsDbDTOList()) {
                if (contributionDetailsDbDTO.getAchContributionId() != null) {
                    contributionID = contributionDetailsDbDTO.getContributionFilePrtId();
                    processAchContribution(
                            contributionFileDTOList,
                            contributionDetailsDbDTO,
                            contributionID,
                            previousContributionID,
                            contributionRecordList,
                            contributionFileDTO);
                } else {
                    addEmptyAchContribution(contributionDetailsDbDTO, contributionFileDTOList);
                }
                previousContributionID = contributionID;
            }
        }
    }

    private void processAchContribution(
            List<ContributionFileDTO> contributionFileDTOList,
            ContributionDetailsDbDTO contributionDetailsDbDTO,
            String contributionID,
            String previousContributionId,
            List<ContributionRecordDTO> contributionRecordList,
            ContributionFileDTO contributionFileDTO) {
        if (!contributionID.equalsIgnoreCase(previousContributionId)) {
            contributionRecordList.add(buildContributionRecord(contributionDetailsDbDTO));
            contributionFileDTO = new ContributionFileDTO();
            contributionFileDTO.setContributionFileId(
                    contributionDetailsDbDTO.getContributionFilePrtId());
            contributionFileDTO.setContributionRecords(contributionRecordList);

            var statusDTO = getStatus(ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED);
            contributionFileDTO.setContributionFileStatus(statusDTO);

            contributionFileDTOList.add(contributionFileDTO);
        } else {
            contributionRecordList.add(buildContributionRecord(contributionDetailsDbDTO));
            contributionFileDTO.setContributionRecords(contributionRecordList);
        }
    }

    private void addEmptyAchContribution(
            ContributionDetailsDbDTO contributionDetailsDbDTO,
            List<ContributionFileDTO> contributionFileDTOList) {
        var contributionFileDTO = new ContributionFileDTO();
        contributionFileDTO.setContributionFileId(
                contributionDetailsDbDTO.getContributionFilePrtId());
        contributionFileDTO.setContributionRecords(Collections.emptyList());
        contributionFileDTO.setContributionFileStatus(
                getStatus(ContributionInfoConstants.RECORD_NOT_FOUND_ERROR_CODE));
        contributionFileDTOList.add(contributionFileDTO);
    }

    private ContributionRecordDTO buildContributionRecord(
            ContributionDetailsDbDTO contributionDetailsDbDTO) {
        var contributionRecordDTO = new ContributionRecordDTO();
        contributionRecordDTO.setPersonName(buildPersonName(contributionDetailsDbDTO));
        contributionRecordDTO.setAccountHolderSsn(contributionDetailsDbDTO.getAccountHolderSSN());
        contributionRecordDTO.setContributionAmount(
                BigDecimal.valueOf(contributionDetailsDbDTO.getContributionAmount()));
        contributionRecordDTO.setValidIndicator(contributionDetailsDbDTO.getValidIndicator());

        var strContributionType = contributionDetailsDbDTO.getContributionType();
        contributionRecordDTO.setContributionType(
                buildContributionType(
                        contributionDetailsDbDTO.getContributionYear(), strContributionType));
        contributionRecordDTO.setInvalidReasons(contributionDetailsDbDTO.getInvalidReasonList());

        return contributionRecordDTO;
    }

    private PersonNameDTO buildPersonName(ContributionDetailsDbDTO contributionDetailsDbDTO) {
        var personDTO = new PersonNameDTO();
        if (Objects.nonNull(contributionDetailsDbDTO.getAccountHolderFirstNm())) {
            personDTO.setPersonFirstName(contributionDetailsDbDTO.getAccountHolderFirstNm());
        } else {
            personDTO.setPersonFirstName("");
        }
        if (Objects.nonNull(contributionDetailsDbDTO.getAccountHolderLastNm())) {
            personDTO.setPersonLastName(contributionDetailsDbDTO.getAccountHolderLastNm());
        } else {
            personDTO.setPersonLastName("");
        }
        if (Objects.nonNull(contributionDetailsDbDTO.getAccountHolderMilddleNm())) {
            personDTO.setPersonMiddleName(contributionDetailsDbDTO.getAccountHolderMilddleNm());
        } else {
            personDTO.setPersonMiddleName("");
        }
        return personDTO;
    }

    private ContributionTypeDTO buildContributionType(
            String strContributionYear, String strContributionType) {
        String typeCode = null;
        String typeCodeName = null;
        var contributionType = new ContributionTypeDTO();
        if (strContributionYear
                .trim()
                .equalsIgnoreCase(ContributionType.CURRENT_YEAR.getContributionTypeName())) {
            if (strContributionType
                    .trim()
                    .equalsIgnoreCase(ContributionType.PARTICIPANT.getContributionTypeName())) {
                typeCode = ContributionTypeCodes.TYPE_CODE_001.getContributionTypeCode();
                typeCodeName = ContributionType.CURRENT_YEAR_PARTICIPANT.getContributionTypeName();
            } else if (strContributionType
                    .trim()
                    .equalsIgnoreCase(ContributionType.EMPLOYER.getContributionTypeName())) {
                typeCode = ContributionTypeCodes.TYPE_CODE_003.getContributionTypeCode();
                typeCodeName = ContributionType.CURRENT_YEAR_EMPLOYER.getContributionTypeName();
            }
        } else if (strContributionYear
                .trim()
                .equalsIgnoreCase(ContributionType.PREVIOUS_YEAR.getContributionTypeName())) {
            if (strContributionType
                    .trim()
                    .equalsIgnoreCase(ContributionType.PARTICIPANT.getContributionTypeName())) {
                typeCode = ContributionTypeCodes.TYPE_CODE_002.getContributionTypeCode();
                typeCodeName = ContributionType.PREVIOUS_YEAR_PARTICIPANT.getContributionTypeName();
            } else if (strContributionType
                    .trim()
                    .equalsIgnoreCase(ContributionType.EMPLOYER.getContributionTypeName())) {
                typeCode = ContributionTypeCodes.TYPE_CODE_004.getContributionTypeCode();
                typeCodeName = ContributionType.PREVIOUS_YEAR_EMPLOYER.getContributionTypeName();
            }
        } else if (strContributionYear
                .trim()
                .equalsIgnoreCase(ContributionInfoConstants.EMPTY_STRING)) {

            if (strContributionType
                    .trim()
                    .equalsIgnoreCase(
                            ContributionType.ROLLOVER_CONTRIBUTION.getContributionTypeName())) {
                typeCode = ContributionTypeCodes.TYPE_CODE_005.getContributionTypeCode();
                typeCodeName = ContributionType.ROLLOVER.getContributionTypeName();
            } else if (strContributionType
                    .trim()
                    .equalsIgnoreCase(
                            ContributionType.INVALID_CONTRIBUTION.getContributionTypeName())) {
                typeCode = ContributionTypeCodes.TYPE_CODE_006.getContributionTypeCode();
                typeCodeName = ContributionType.INVALID_CONTRIBUTION_TYPE.getContributionTypeName();
            }
        }

        contributionType.setCode(typeCode);
        contributionType.setCodeName(typeCodeName);
        return contributionType;
    }

    private StatusDTO getStatus(int messageType) {
        var statusDTO = new StatusDTO();
        var statusMessageType = serviceMessageTypeRepository.getStatusMessageType(messageType);
        statusDTO.setStatusDescription(statusMessageType.getStatusDescription());
        statusDTO.setStatusCode(statusMessageType.getStatusCode());
        statusDTO.setSeverity(statusMessageType.getSeverity());
        return statusDTO;
    }
}
