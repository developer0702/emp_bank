package com.optum.ftps.ob.core.contribution.data;

import com.optum.ftps.ob.core.contribution.model.v1.FileDetails;

public class FileDetailsData {
    public static FileDetails getFileDetails() {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setContributionFileId("file1");
        fileDetails.setRequestType("A");
        return fileDetails;
    }
}
