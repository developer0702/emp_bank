package com.optum.ftps.ob.core.contribution.repository;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;

import java.util.Date;
import java.util.List;

public interface ContributionInfoRepository {
    List<ContributionDetailsDbDTO> getValidContributionRecords(String contributionFileId);

    List<ContributionDetailsDbDTO> getInvalidContributionRecords(String contributionFileId);

    List<ContributionDetailsDbDTO> getAllContributionRecords(String contributionFileId);

    List<ContributionSummaryDbDTO> getFundYDateRangeMaxFiles(
            String employerGroupId, Integer maxNoOfFiles, Date dateFrom, Date dateTo);

    List<ContributionSummaryDbDTO> getFundYMaxFiles(
            String employerGroupId, Integer maxNoOfFiles, Date dateTo);

    List<ContributionSummaryDbDTO> getFundNDateRangeMaxFiles(
            String employerGroupId, Integer maxNoOfFiles, Date dateFrom, Date dateTo);

    List<ContributionSummaryDbDTO> getFundNMaxFiles(String employerGroupId, Integer maxNoOfFiles);
}
