package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryRequestDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionSummaryRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionSummaryRequestMapper {

    @Mapping(target = "employerGroupId", source = "employerGroupId")
    @Mapping(target = "restrictToFunded", source = "restrictToFunded")
    @Mapping(target = "dateFrom", source = "dateFrom")
    @Mapping(target = "dateTo", source = "dateTo")
    @Mapping(target = "maxNumberOfFiles", source = "maxNumberOfFiles")
    @Mapping(target = "requestId", source = "requestId")
    ContributionSummaryRequestDTO contrbSummaryDTO(ContributionSummaryRequest request);
}
