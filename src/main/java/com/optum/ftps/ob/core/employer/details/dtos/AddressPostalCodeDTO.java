package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

@Data
public class AddressPostalCodeDTO {

    private String zipCode;

    private String zipPlus4;

    private String postalCode;

    public AddressPostalCodeDTO(String zipCode, String zipPlus4, String postalCode) {}
}
