package com.optum.ftps.ob.core.contribution.dtos.contributioninfo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusMessageType {
    private String statusCode;
    private String statusDescription;
    private String severity;
}
