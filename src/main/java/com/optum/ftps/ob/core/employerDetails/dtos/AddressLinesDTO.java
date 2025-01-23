package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class AddressLinesDTO {

    private String addressDeliveryLine1;

    private String addressDeliveryLine2;

    private String addressCity;

    private AddressStateDTO addressState;

    private AddressPostalCodeDTO addressPostalCode;
}
