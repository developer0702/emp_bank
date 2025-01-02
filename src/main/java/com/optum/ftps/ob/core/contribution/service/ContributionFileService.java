package com.optum.ftps.ob.core.contribution.service;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileTransactionResponseDTO;

import java.util.List;

public interface ContributionFileService {
    ContributionFileHistoryResponseDTO getEmployerContributionFileHistory(
            String customerPolicyNumber, String effectiveTransactionDate);

    ContributionFileNamesResponseDTO getContributionFileNames(
            List<String> contributionFilePortalId);

    ContributionFileTransactionResponseDTO getEmployerContributionFileTransactions(
            String contributionFilePortalId);

    ContributionFileStatusResponseDTO updateContributionFileStatus(
            ContributionFileStatusDTO contributionFileStatusDTO);
}
