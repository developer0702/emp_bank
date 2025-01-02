package com.optum.ftps.ob.core.contribution.repository.impl;

import static com.optum.ftps.ob.core.contribution.util.DbUtil.getDouble;
import static com.optum.ftps.ob.core.contribution.util.DbUtil.getInteger;
import static com.optum.ftps.ob.core.contribution.util.DbUtil.getString;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoQueryConstants;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;
import com.optum.ftps.ob.core.contribution.repository.ContributionInfoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class ContributionInfoRepositoryImpl implements ContributionInfoRepository {
    @PersistenceContext private EntityManager entityManager;

    private static final String CONTRIBUTION_FILE_ID = "contributionFileId";
    private static final String FETCH_FIRST = " FETCH FIRST  ";
    private static final String ROWS_ONLY = " ROWS ONLY";
    private static final String EMPLOYER_GROUP_ID = "employerGroupId";
    private static final String DATE_FROM = "dateFrom";
    private static final String DATE_TO = "dateTo";
    private static final String TOP = " TOP ";
    private static final String SPACE = " ";

    @Override
    public List<ContributionDetailsDbDTO> getValidContributionRecords(String contributionFileId) {
        log.info("Getting valid contribution records {}", contributionFileId);
        var builder =
                new StringBuilder()
                        .append(ContributionInfoQueryConstants.GET_VALID_CONTRIBUTION_RECS);

        var query = entityManager.createNativeQuery(builder.toString());
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileId);

        var rows = query.getResultList();
        return mapValidContributionRecords(rows);
    }

    @Override
    public List<ContributionDetailsDbDTO> getInvalidContributionRecords(String contributionFileId) {
        log.info("Getting invalid contribution records {}", contributionFileId);
        var builder =
                new StringBuilder()
                        .append(ContributionInfoQueryConstants.GET_INAVLID_CONTRIBUTION_RECS);

        var query = entityManager.createNativeQuery(builder.toString());
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileId);

        var rows = query.getResultList();
        return mapInvalidContributionRecords(rows);
    }

    @Override
    public List<ContributionDetailsDbDTO> getAllContributionRecords(String contributionFileId) {
        log.info("Getting all contribution records {}", contributionFileId);
        var builder =
                new StringBuilder()
                        .append(ContributionInfoQueryConstants.GET_ALL_CONTRIBUTION_RECS);

        var query = entityManager.createNativeQuery(builder.toString());
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileId);

        var rows = query.getResultList();
        return mapInvalidContributionRecords(rows);
    }

    public List<ContributionSummaryDbDTO> getFundYDateRangeMaxFiles(
            String employerGroupId, Integer maxNoOfFiles, Date dateFrom, Date dateTo) {
        log.info("Getting data in date range max files with fund id Y");

        String sql = ContributionInfoQueryConstants.GET_FUNDIND_Y_DATE_RANGE_MAX_NO_OF_FILES;
        if (Objects.nonNull(maxNoOfFiles) && maxNoOfFiles > 0) {
            sql = TOP + maxNoOfFiles + SPACE + sql;
        }

        var query = entityManager.createNativeQuery(sql);
        query.setParameter(EMPLOYER_GROUP_ID, employerGroupId);
        query.setParameter(DATE_FROM, new java.sql.Date(dateFrom.getTime()));
        query.setParameter(DATE_TO, new java.sql.Date(dateTo.getTime()));

        var rows = query.getResultList();
        return mapContributionSummaryData(rows);
    }

    public List<ContributionSummaryDbDTO> getFundYMaxFiles(
            String employerGroupId, Integer maxNoOfFiles, Date dateTo) {
        log.info("Getting data of max files with fund id Y");

        String sql = ContributionInfoQueryConstants.GET_FUNDIND_Y_MAX_NO_OF_FILES;
        if (Objects.nonNull(maxNoOfFiles) && maxNoOfFiles > 0) {
            sql = TOP + maxNoOfFiles + SPACE + sql;
        }

        var query = entityManager.createNativeQuery(sql);
        query.setParameter(EMPLOYER_GROUP_ID, employerGroupId);
        query.setParameter(DATE_FROM, new java.sql.Date(dateTo.getTime()));

        var rows = query.getResultList();
        return mapContributionSummaryData(rows);
    }

    public List<ContributionSummaryDbDTO> getFundNDateRangeMaxFiles(
            String employerGroupId, Integer maxNoOfFiles, Date dateFrom, Date dateTo) {
        log.info("Getting data in date range max files with fund id N");

        String sql = ContributionInfoQueryConstants.GET_FUNDIND_N_DATE_RANGE_MAX_NO_OF_FILES;
        if (Objects.nonNull(maxNoOfFiles) && maxNoOfFiles > 0) {
            sql = TOP + maxNoOfFiles + SPACE + sql;
        }

        var query = entityManager.createNativeQuery(sql);
        query.setParameter(EMPLOYER_GROUP_ID, employerGroupId);
        query.setParameter(DATE_FROM, new java.sql.Date(dateFrom.getTime()));
        query.setParameter(DATE_TO, new java.sql.Date(dateTo.getTime()));

        var rows = query.getResultList();
        return mapContributionSummaryData(rows);
    }

    public List<ContributionSummaryDbDTO> getFundNMaxFiles(
            String employerGroupId, Integer maxNoOfFiles) {
        log.info("Getting data of max files with fund id N");

        String sql = ContributionInfoQueryConstants.GET_FUNDIND_N_MAX_NO_OF_FILES;
        if (Objects.nonNull(maxNoOfFiles) && maxNoOfFiles > 0) {
            sql = TOP + maxNoOfFiles + SPACE + sql;
        }

        var query = entityManager.createNativeQuery(sql);
        query.setParameter(EMPLOYER_GROUP_ID, employerGroupId);

        var rows = query.getResultList();
        return mapContributionSummaryData(rows);
    }

    public List<ContributionDetailsDbDTO> mapValidContributionRecords(List<Object[]> queryResult) {
        List<ContributionDetailsDbDTO> records = new ArrayList<>();
        for (var row : queryResult) {
            ContributionDetailsDbDTO dbDTO =
                    ContributionDetailsDbDTO.builder()
                            .achContributionId(getString(row, 0))
                            .contributionFilePrtId(getString(row, 1))
                            .accountHolderSSN(getString(row, 2))
                            .accountHolderFirstNm(getString(row, 3))
                            .accountHolderMilddleNm(getString(row, 4))
                            .accountHolderLastNm(getString(row, 5))
                            .contributionAmount(getDouble(row, 6))
                            .contributionType(getString(row, 7))
                            .validIndicator(getString(row, 8))
                            .contributionYear(getString(row, 9))
                            .build();
            records.add(dbDTO);
        }
        return records;
    }

    public List<ContributionDetailsDbDTO> mapInvalidContributionRecords(
            List<Object[]> queryResult) {
        List<ContributionDetailsDbDTO> records = new ArrayList<>();
        for (var row : queryResult) {
            ContributionDetailsDbDTO dbDTO =
                    ContributionDetailsDbDTO.builder()
                            .achContributionId(getString(row, 0))
                            .contributionFilePrtId(getString(row, 1))
                            .accountHolderSSN(getString(row, 2))
                            .accountHolderFirstNm(getString(row, 3))
                            .accountHolderMilddleNm(getString(row, 4))
                            .accountHolderLastNm(getString(row, 5))
                            .contributionAmount(getDouble(row, 6))
                            .contributionType(getString(row, 7))
                            .validIndicator(getString(row, 8))
                            .contributionYear(getString(row, 9))
                            .errorCode(getString(row, 10))
                            .errorText(getString(row, 11))
                            .build();
            records.add(dbDTO);
        }
        return records;
    }

    public List<ContributionSummaryDbDTO> mapContributionSummaryData(List<Object[]> queryResult) {
        List<ContributionSummaryDbDTO> records = new ArrayList<>();
        for (var row : queryResult) {
            ContributionSummaryDbDTO dbDTO =
                    ContributionSummaryDbDTO.builder()
                            .contributionFilePrtlId(getString(row, 0))
                            .customerPolicyNumber(getString(row, 1))
                            .submittedDate(getString(row, 2))
                            .effectiveDate(getString(row, 3))
                            .validRecordCount(getInteger(row, 4))
                            .totalValidContributionAmount(getDouble(row, 5))
                            .invalidRecordCount(getInteger(row, 6))
                            .invalidTotalValidContributionAmount(getDouble(row, 7))
                            .serviceReasonCode(getString(row, 8))
                            .serviceMessageSeverityCode(getString(row, 9))
                            .serviceMessageText(getString(row, 10))
                            .build();
            records.add(dbDTO);
        }
        return records;
    }
}
