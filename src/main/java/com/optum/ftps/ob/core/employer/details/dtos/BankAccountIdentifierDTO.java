package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankAccountIdentifierDTO {

    private String bankAccountNumber;

    private String bankRoutingNumber;

    public BankAccountIdentifierDTO(String string, String string1) {}
}
