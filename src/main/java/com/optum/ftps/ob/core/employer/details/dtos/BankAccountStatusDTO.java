package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankAccountStatusDTO {

    private String code;

    private String codeName;

    public BankAccountStatusDTO(String code, String codeName) {}
}
