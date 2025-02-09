package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployerDTO {
    private String source;
    private String correlationId;
    private int id;
    private String code;
    private String name;
    private String status;
    private int marketSegment;
    private List<AddressDTO> addresses;
    private List<BankAccountDTO> bankAccounts;
    private List<ContactPersonDTO> contactPersons;
    private List<ProducerDTO> producers;
    private LocalDateTime effectiveDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
