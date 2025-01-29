package com.optum.ftps.ob.core.employerDetails.service.impl;

import com.optum.ftps.ob.core.employerDetails.exceptions.ValidationException;
import com.optum.ftps.ob.core.employerDetails.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.employerDetails.service.ExceptionService;

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

    @Override
    public void handleValidationError(Collection<Integer> codes) throws ValidationException {
        if (!codes.isEmpty()) {
            log.debug("handling validation codes : {}", codes);
            var errors = codes.stream().toList();
            throw new ValidationException("Validation error occurred", errors);
        }
    }

    @Override
    public ErrorItem getError(Integer errorCode) {
        log.debug("Getting error for code : {}", errorCode);
        return errorMap.get(errorCode);
    }
}
