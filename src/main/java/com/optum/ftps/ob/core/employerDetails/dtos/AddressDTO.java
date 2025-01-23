package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class AddressDTO {

    private AddressTypeCodeDTO addressTypeCode;

    private String primaryAddressIndicator;

    private AddressLinesDTO addressLines;
}
