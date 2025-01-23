package com.optum.ftps.ob.core.employerDetails.repository.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsRequestDTO;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerBankDetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class EmployerBankDetailsRepositoryImpl implements EmployerBankDetailsRepository {

    @PersistenceContext private final EntityManager entityManager;

    @Override
    public EmployerBankDetailsRequestDTO updateEmployerBankDetails(
            EmployerBankDetailsRequestDTO employerBankDetailsRequest) {
        return null;
    }
}
