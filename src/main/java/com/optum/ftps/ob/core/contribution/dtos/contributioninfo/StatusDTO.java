package com.optum.ftps.ob.core.contribution.dtos.contributioninfo;

import lombok.Data;

@Data
public class StatusDTO {
    private String statusCode;
    private String severity;
    private String statusDescription;
}
