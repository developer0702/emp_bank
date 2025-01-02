package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionRecordDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionDetailsResponse;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionRecord;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionDetailsResponseMapper {

    @Mapping(target = "contributionFiles", source = "contributionFiles")
    @Mapping(target = "contrbDetailStatus", source = "contrbDetailStatus")
    ContributionDetailsResponse contrbDetailsResponse(
            ContributionDetailsResponseDTO contributionDetailsResponseDTO);

    ContributionRecord map(ContributionRecordDTO dto);
}
