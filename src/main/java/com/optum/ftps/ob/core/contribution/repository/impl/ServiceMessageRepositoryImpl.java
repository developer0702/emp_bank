package com.optum.ftps.ob.core.contribution.repository.impl;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ServiceMessageRepositoryImpl implements ServiceMessageTypeRepository {
    @PersistenceContext private EntityManager entityManager;

    @Override
    public StatusMessageType getStatusMessageType(int statusCode) {
        log.info("Getting status type for the status {}", statusCode);
        var builder =
                new StringBuilder()
                        .append(
                                " SELECT service_reason_code as statusReasonCode,"
                                        + " service_message_text as"
                                        + " statusDescription,service_message_severity_code as"
                                        + " severity")
                        .append(
                                " FROM contribution_service.service_message_type WHERE"
                                        + " service_message_id = :statusCode ");

        var query = entityManager.createNativeQuery(builder.toString());
        query.setParameter("statusCode", statusCode);

        var rows = query.getResultList();
        return mapServiceMessageResult(rows);
    }

    public static StatusMessageType mapServiceMessageResult(List<Object[]> queryResult) {

        if (queryResult.isEmpty()) {
            return null;
        }
        var row = queryResult.get(0);
        return StatusMessageType.builder()
                .statusCode(getString(row, 0))
                .statusDescription(getString(row, 1))
                .severity(getString(row, 2))
                .build();
    }

    private static String getString(Object[] row, int index) {
        return row[index] != null ? row[index].toString().trim() : null;
    }
}
