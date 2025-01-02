package com.optum.ftps.ob.core.contribution.repository;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;

public interface ServiceMessageTypeRepository {
    StatusMessageType getStatusMessageType(int statusCode);
}
