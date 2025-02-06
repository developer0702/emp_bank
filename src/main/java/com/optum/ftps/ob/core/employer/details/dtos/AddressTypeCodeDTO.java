package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

@Data
public class AddressTypeCodeDTO {

    private String code;

    private String codeName;

    public AddressTypeCodeDTO(String code, String codeName) {}
}
