package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.constants.FileDetailsRequestType;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.FileDetailsDTO;

public class FileDetailsDTOData {
    public static FileDetailsDTO getFileDetails() {
        return new FileDetailsDTO("file1", FileDetailsRequestType.VALID_REQ.getRequestType());
    }
}
