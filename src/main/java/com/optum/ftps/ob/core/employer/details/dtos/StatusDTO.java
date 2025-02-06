package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

@Data
public class StatusDTO {
    private String statusCode;
    private String severity;
    private String statusDescription;
}
