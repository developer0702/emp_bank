package com.optum.ftps.ob.core.contribution.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusResponseDTO;
import com.optum.ftps.ob.core.contribution.model.v1.UpdateContributionFileStatusResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface ContributionFileStatusResponseMapper {

    UpdateContributionFileStatusResponse map(ContributionFileStatusResponseDTO responseDTO);
}
