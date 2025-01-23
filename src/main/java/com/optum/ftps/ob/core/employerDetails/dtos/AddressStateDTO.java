package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

@Data
public class AddressStateDTO {

    private String stateCode;

    private String stateName;

    public AddressStateDTO(String stateCode, String stateName) {}
}
