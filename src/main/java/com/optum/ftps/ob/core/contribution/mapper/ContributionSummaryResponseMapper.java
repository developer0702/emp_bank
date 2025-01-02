package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryResponseDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionSummaryResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionSummaryResponseMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "requestId", source = "requestId")
    @Mapping(target = "contributionFileSummary", source = "contributionFileSummary")
    ContributionSummaryResponse contrbSummaryResponse(ContributionSummaryResponseDTO dto);
}
