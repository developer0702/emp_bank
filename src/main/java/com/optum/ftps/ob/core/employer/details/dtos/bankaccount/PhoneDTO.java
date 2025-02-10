package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhoneDTO {
    private String source;
    private String correlationId;
    private int id;
    private String phoneType;
    private String areaCode;
    private String phoneNum;
    private String countryCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
