package com.optum.ftps.ob.core.contribution.repository;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryRecordsDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileItemDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDbDTO;

import java.util.List;

public interface ContributionFileRepository {
    List<ContributionFileHistoryRecordsDTO> getEmployerContributionFileHistory(
            String customerPolicyNumber, String effectiveTransactionDate);

    List<ContributionFileNamesDTO> getContributionFileNames(List<String> contributionFilePortalId);

    List<ContributionFileItemDTO> getEmployerContributionFileTransactions(
            String contributionFilePortalId);

    List<ContributionFileStatusDbDTO> getContributionFileStatus(
            ContributionFileStatusDTO contributionFileStatusDTO);

    boolean isValidFundId(ContributionFileStatusDTO contributionFileStatusDTO);

    int approveContributionFile(ContributionFileStatusDTO contributionFileStatusDTO);

    int denyContributionFile(ContributionFileStatusDTO contributionFileStatusDTO);
}
