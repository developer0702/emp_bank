package com.optum.ftps.ob.core.contribution.dtos.contributionFile;

import lombok.Builder;

@Builder
public record ContributionFileStatusDbDTO(String contributionFileId, String statusCode) {}
