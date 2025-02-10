package com.optum.ftps.ob.core.employerDetails.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankAccountDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerId;
    private String usage;
    private String bankName;
    private String routingNumber;
    private String accountNumber;
    private String accountType;
    private String nickName;
    private String accountStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
