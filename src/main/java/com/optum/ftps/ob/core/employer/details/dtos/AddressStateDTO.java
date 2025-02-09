package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

@Data
public class AddressStateDTO {

    private String stateCode;

    private String stateName;

    public AddressStateDTO(String stateCode, String stateName) {}
}
