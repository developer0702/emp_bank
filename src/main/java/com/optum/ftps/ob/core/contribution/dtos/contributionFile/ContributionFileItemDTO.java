package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

public record ContributionFileItemDTO(
        String contributionFilePortalId,
        String accountNumber,
        String transactionDescription,
        String ssn,
        String crAmt,
        String contributionTypeName,
        String portalContributionCommitText) {}
