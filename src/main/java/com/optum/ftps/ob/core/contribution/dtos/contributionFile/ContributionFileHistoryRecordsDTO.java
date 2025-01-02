package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

public record ContributionFileHistoryRecordsDTO(
        String contributionFilePortalId,
        String effectiveTransactionDate,
        String statusCode,
        String submittedMethodCode,
        String totalFileContributionAmount,
        String originalFileName,
        String newFileName,
        String friendlyFileName,
        String submittedByName,
        String submittedTimestamp,
        String approvedByName,
        String approvedTimestamp,
        String denyByName,
        String denyTimestamp,
        String denyCommitText,
        String processedTimestamp,
        String customerPolicyNumber,
        String approverEmailAndTimestamp,
        String customerFundId,
        String bankName,
        String bankNickName) {}
