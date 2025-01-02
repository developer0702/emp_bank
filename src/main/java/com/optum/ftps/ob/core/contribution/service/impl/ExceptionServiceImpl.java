package com.optum.ftps.ob.core.contribution.service.impl;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.contribution.mapper.ErrorItemMapper;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {

    private final Map<Integer, ErrorItem> errorMap = new ConcurrentHashMap<>();
    private final ErrorItemMapper errorItemMapper;
    private final ServiceMessageTypeRepository serviceMessageTypeRepository;

    @Override
    public void handleValidationError(Collection<Integer> codes) {
        if (!codes.isEmpty()) {
            log.debug("handling validation codes : {}", codes);
            var errors = codes.stream().map(this::getError).toList();
            throw new ValidationException(errors);
        }
    }

    @Override
    public ErrorItem getError(Integer errorCode) {
        log.debug("Getting error for code : {}", errorCode);
        return errorMap.computeIfAbsent(
                errorCode,
                k -> {
                    StatusMessageType messageType =
                            serviceMessageTypeRepository.getStatusMessageType(errorCode);
                    return errorItemMapper.map(messageType);
                });
    }
}
