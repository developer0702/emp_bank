package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressDTO {
    private String source;
    private String correlationId;
    private int id;
    private int objectId;
    private String addressType;
    private String streetAdd1;
    private String streetAdd2;
    private String cityName;
    private String stateCode;
    private String zipCode;
    private String country;
    private LocalDateTime effectiveDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
