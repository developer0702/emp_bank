package com.optum.ftps.ob.core.contribution.repository.impl;

import com.optum.ftps.ob.core.contribution.constants.ContributionFileQueryConstants;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryRecordsDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileItemDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDbDTO;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileNamesDbToDTOMapper;
import com.optum.ftps.ob.core.contribution.repository.ContributionFileRepository;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionFileHistoryDbtoDTOMapper;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionFileStatusDbtoDTOMapper;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionFileTransactionDbToDTOMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContributionFileRepositoryImpl implements ContributionFileRepository {
    private static final String CONTRIBUTION_FILE_ID = "contributionFileId";

    private final ContributionFileHistoryDbtoDTOMapper contributionFileHistoryDbtoDTOMapper;
    @PersistenceContext private final EntityManager entityManager;
    private final ContributionFileNamesDbToDTOMapper contributionFileNamesDbToDTOMapper;
    private final ContributionFileTransactionDbToDTOMapper contributionFileTransactiondbToDTOMapper;
    private final ContributionFileStatusDbtoDTOMapper contributionFileStatusDbtoDTOMapper;

    @Override
    @SuppressWarnings("unchecked")
    public List<ContributionFileHistoryRecordsDTO> getEmployerContributionFileHistory(
            String customerPolicyNumber, String effectiveTransactionDate) {
        log.info(
                "getEmployerContributionFileHistory called with customerPolicyNumber: {} and"
                        + " effectiveTransactionDate: {}",
                customerPolicyNumber,
                effectiveTransactionDate);

        var query =
                entityManager.createNativeQuery(
                        ContributionFileQueryConstants
                                .SQL_SELECT_HISTORY_FROM_CONTRIBUTION_PORTAL_FILE);
        query.setParameter("customerPolicyNumber", customerPolicyNumber);
        query.setParameter("effectiveTransactionDate", effectiveTransactionDate);

        return contributionFileHistoryDbtoDTOMapper.mapToContributionFileHistoryResponseDTO(
                query.getResultList());
    }

    @Override
    public List<ContributionFileNamesDTO> getContributionFileNames(
            List<String> contributionFilePortalId) {
        log.info("Getting file names for {}", contributionFilePortalId);
        if (contributionFilePortalId.isEmpty()) {
            log.error(
                    "Contribution file portal IDs is empty, this should not happen since it is"
                            + " validated in the controller");
            return new ArrayList<>();
        }

        var builder =
                new StringBuilder()
                        .append(ContributionFileQueryConstants.CONTRIBUTION_FILE_NAMES)
                        .append(ContributionFileQueryConstants.CONTRIBUTION_FILE_CONDITION);
        for (int i = 0; i < contributionFilePortalId.size(); i++) {
            builder.append("?");
            if (i < contributionFilePortalId.size() - 1) {
                builder.append(",");
            }
        }
        builder.append(ContributionFileQueryConstants.BRACKET);

        var query = entityManager.createNativeQuery(builder.toString());
        for (int i = 0; i < contributionFilePortalId.size(); i++) {
            query.setParameter(i + 1, contributionFilePortalId.get(i));
        }

        var rows = query.getResultList();
        return contributionFileNamesDbToDTOMapper.mapContributionFileNamesDbToDTO(rows);
    }

    @Override
    public List<ContributionFileItemDTO> getEmployerContributionFileTransactions(
            String contributionFilePortalId) {
        log.info("Getting contribution file transactions for {}", contributionFilePortalId);
        var builder =
                new StringBuilder()
                        .append(
                                ContributionFileQueryConstants
                                        .SELECT_SQL_CONTRIBUTION_FILE_TRANSACTION_STATUS);

        var query = entityManager.createNativeQuery(builder.toString());
        query.setParameter("contributionFilePortalId", contributionFilePortalId);

        return contributionFileTransactiondbToDTOMapper.mapToContributionFileTransactionResponseDTO(
                query.getResultList());
    }

    @Override
    public List<ContributionFileStatusDbDTO> getContributionFileStatus(
            ContributionFileStatusDTO contributionFileStatusDTO) {
        log.info(
                "getContributionFileStatus called with fileId: {}",
                contributionFileStatusDTO.contributionFileId());
        var query =
                entityManager.createNativeQuery(
                        ContributionFileQueryConstants.SELECT_CONTRIB_FILE_STS_CD_BY_ID);
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileStatusDTO.contributionFileId());
        return contributionFileStatusDbtoDTOMapper.map(query.getResultList());
    }

    @Override
    public boolean isValidFundId(ContributionFileStatusDTO contributionFileStatusDTO) {
        log.info(
                "isValidFundId called with fileId: {} and fundId: {}",
                contributionFileStatusDTO.contributionFileId(),
                contributionFileStatusDTO.customerFundId());
        var query =
                entityManager.createNativeQuery(
                        ContributionFileQueryConstants.SELECT_VALID_FUND_ID);
        query.setParameter("fundId", contributionFileStatusDTO.customerFundId());
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileStatusDTO.contributionFileId());
        return !query.getResultList().isEmpty();
    }

    @Override
    public int approveContributionFile(ContributionFileStatusDTO contributionFileStatusDTO) {
        log.info(
                "approveContributionFile called with fileId: {}",
                contributionFileStatusDTO.contributionFileId());
        var query =
                entityManager.createNativeQuery(
                        ContributionFileQueryConstants.UPDATE_CONTRIB_FILE_APPROVED);
        query.setParameter("statusCode", contributionFileStatusDTO.fileStatusCode());
        query.setParameter("fundId", contributionFileStatusDTO.customerFundId());
        query.setParameter("userName", contributionFileStatusDTO.changedByName());
        query.setParameter("effTransDate", contributionFileStatusDTO.effectiveTransactionDate());
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileStatusDTO.contributionFileId());
        return query.executeUpdate();
    }

    @Override
    public int denyContributionFile(ContributionFileStatusDTO contributionFileStatusDTO) {
        log.info(
                "denyContributionFile called with fileId: {}",
                contributionFileStatusDTO.contributionFileId());
        var query =
                entityManager.createNativeQuery(
                        ContributionFileQueryConstants.UPDATE_CONTRIB_FILE_DENIED);
        query.setParameter("statusCode", contributionFileStatusDTO.fileStatusCode());
        query.setParameter("userName", contributionFileStatusDTO.changedByName());
        query.setParameter("comments", contributionFileStatusDTO.commentText());
        query.setParameter(CONTRIBUTION_FILE_ID, contributionFileStatusDTO.contributionFileId());
        return query.executeUpdate();
    }
}
