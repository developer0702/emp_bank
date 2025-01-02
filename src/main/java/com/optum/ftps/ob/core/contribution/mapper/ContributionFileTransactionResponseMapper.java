package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileTransactionResponseDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileTransactionResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionFileTransactionResponseMapper {
    @Mapping(target = "contributionFiles", source = "contributionFiles")
    ContributionFileTransactionResponse contributionFileTransactionResponse(
            ContributionFileTransactionResponseDTO contributionFileTransactionResponseDTO);
}
