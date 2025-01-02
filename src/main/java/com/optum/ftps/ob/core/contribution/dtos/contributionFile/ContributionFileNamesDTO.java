package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

public record ContributionFileNamesDTO(
        String contributionFilePortalId,
        String effectiveTransactionDate,
        String processTimestamp,
        String originalFileName,
        String newFileName,
        String friendlyFileName) {}
