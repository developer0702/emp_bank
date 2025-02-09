package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactRoleAssignmentDTO {
    private String source;
    private String correlationId;
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
