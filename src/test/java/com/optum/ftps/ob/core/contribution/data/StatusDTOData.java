package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusDTO;

public class StatusDTOData {
    public static StatusDTO getStatusDTO() {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatusCode("0000");
        statusDTO.setStatusDescription("Success");
        statusDTO.setSeverity("INFO");
        return statusDTO;
    }
}
