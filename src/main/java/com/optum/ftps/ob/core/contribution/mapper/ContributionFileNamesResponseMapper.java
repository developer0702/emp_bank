package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesResponseDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileName;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileNamesResponse;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionFileNamesResponseMapper {
    ContributionFileNamesResponse mapContributionFileNamesResponse(
            ContributionFileNamesResponseDTO contributionFileNamesResponseDTO);

    ContributionFileName map(ContributionFileNamesDTO dto);
}
