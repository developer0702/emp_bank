package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProducerDTO {
    private String source;
    private String correlationId;
    private int id;
    private int producerId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
