package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerOrgAssoId;
    private int enrollmentMethod;
    private boolean isESign;
    private boolean isWetSignature;
    private boolean isEmployerObtainHsaAffirmation;
}
