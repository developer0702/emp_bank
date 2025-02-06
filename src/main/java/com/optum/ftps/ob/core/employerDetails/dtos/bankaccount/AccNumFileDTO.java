package com.optum.ftps.ob.core.employerDetails.dtos.bankaccount;

import lombok.Data;

@Data
public class AccNumFileDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerOrgAssoId;
    private int isEmployerReceiveAccNumList;
    private String accNumberFileRecipientName;
    private String lastName;
    private int phoneNum;
    private String email;
    private int frequency;
    private int anfTransmissionMethod;
    private String anfRecipientConfirmedFlag;
}
