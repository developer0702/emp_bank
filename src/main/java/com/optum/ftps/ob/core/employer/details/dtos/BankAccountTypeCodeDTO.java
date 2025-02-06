package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankAccountTypeCodeDTO {

    private String code;

    private String codeName;

    public BankAccountTypeCodeDTO(String code, String codeName) {}
}
