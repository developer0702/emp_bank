package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

@Data
public class AddressDTO {

    private AddressTypeCodeDTO addressTypeCode;

    private String primaryAddressIndicator;

    private AddressLinesDTO addressLines;
}
