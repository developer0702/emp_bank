package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

import java.util.Date;

public record ContributionFileStatusDTO(
        Integer contributionFileId,
        Integer customerFundId,
        Date effectiveTransactionDate,
        String changedByName,
        String commentText,
        String fileStatusCode) {}
