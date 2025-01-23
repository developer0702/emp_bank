package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class BankAccountIdentifierDTO {

    private String bankAccountNumber;

    private String bankRoutingNumber;

    public BankAccountIdentifierDTO(String string, String string1) {}
}
