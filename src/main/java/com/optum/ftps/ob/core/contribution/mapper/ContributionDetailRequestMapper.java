package com.optum.ftps.ob.core.contribution.mapper;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsRequestDTO;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionDetailsRequest;
import com.optum.ftps.ob.core.contribution.util.StringUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributionDetailRequestMapper {

    @Named("requestIdCheck")
    default String requestIdCheck(String requestId) {
        // Check if Request ID obtained from client is not null
        if (!StringUtil.isEmpty(requestId)) {
            return requestId;
        }
        return null;
    }

    @Mapping(target = "fileDetails", source = "fileDetails")
    @Mapping(target = "requestId", source = "requestId", qualifiedByName = "requestIdCheck")
    ContributionDetailsRequestDTO map(ContributionDetailsRequest contrbDetailsRequest);
}
