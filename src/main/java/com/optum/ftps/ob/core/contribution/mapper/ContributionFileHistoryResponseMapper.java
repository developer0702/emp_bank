package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryResponseDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileHistoryResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionFileHistoryResponseMapper {
    @Mapping(target = "contributionFileHistoryRecords", source = "contributionFileHistoryRecords")
    ContributionFileHistoryResponse contributionFileHistoryResponse(
            ContributionFileHistoryResponseDTO contributionDetailsResponseDTO);
}
