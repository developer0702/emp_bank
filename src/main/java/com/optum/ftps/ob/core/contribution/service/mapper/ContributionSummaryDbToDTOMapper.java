package com.optum.ftps.ob.core.contribution.service.mapper;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionFileSummaryDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContributionSummaryDbToDTOMapper {
    private final ServiceMessageTypeRepository serviceMessageTypeRepository;

    public ContributionSummaryResponseDTO mapContributionSummaryDbToDTO(
            List<ContributionFileSummaryDTO> contributionFileSummaryDb, String requestId) {
        var contributionSummaryResponseDTO = new ContributionSummaryResponseDTO();
        contributionSummaryResponseDTO.setContributionFileSummary(contributionFileSummaryDb);
        contributionSummaryResponseDTO.setRequestId(requestId);

        var serviceMessageType =
                serviceMessageTypeRepository.getStatusMessageType(
                        ContributionInfoConstants.REQUEST_SUCCESSFULLY_PROCESSED);
        var statusDTO = new StatusDTO();
        statusDTO.setSeverity(serviceMessageType.getSeverity());
        statusDTO.setStatusCode(serviceMessageType.getStatusCode());
        statusDTO.setStatusDescription(serviceMessageType.getStatusDescription());
        contributionSummaryResponseDTO.setStatus(statusDTO);
        return contributionSummaryResponseDTO;
    }
}
