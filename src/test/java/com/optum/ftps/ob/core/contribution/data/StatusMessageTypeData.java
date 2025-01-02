package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;

public class StatusMessageTypeData {

    public static StatusMessageType getStatusMessageType() {
        return StatusMessageType.builder()
                .statusCode("0000")
                .statusDescription("Success")
                .severity("Info")
                .build();
    }
}
