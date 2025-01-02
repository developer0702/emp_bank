package com.optum.ftps.ob.core.contribution.mapper;

import static com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants.DATE_FORMAT;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.model.v1.UpdateContributionFileStatusRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface ContributionFileStatusMapper {

    @Mapping(target = "effectiveTransactionDate", dateFormat = DATE_FORMAT)
    ContributionFileStatusDTO map(UpdateContributionFileStatusRequest request);
}
