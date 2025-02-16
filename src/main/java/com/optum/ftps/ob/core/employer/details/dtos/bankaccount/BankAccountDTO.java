package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankAccountDTO {
    @JsonProperty("source")
    private String source;

    @JsonProperty("correlationId")
    private String correlationId;

    @JsonProperty("usage")
    private String usage;

    @JsonProperty("bankName")
    private String bankName;

    @JsonProperty("routingNumber")
    private String routingNumber;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountType")
    private String accountType;

    @JsonProperty("nickName")
    private String nickName;

    @JsonProperty("accountStatus")
    private String accountStatus;

    @JsonProperty("bankId") // 🔹 Required for update API
    private Long bankId;
}
