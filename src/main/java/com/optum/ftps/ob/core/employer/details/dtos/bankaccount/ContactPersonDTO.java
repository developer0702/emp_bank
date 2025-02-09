package com.optum.ftps.ob.core.employer.details.dtos.bankaccount;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContactPersonDTO {
    private String source;
    private String correlationId;
    private int id;
    private int employerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String title;
    private boolean isPrimaryContact;
    private String emailAddress;
    private String sourceNoteText;
    private String vendorNoteText;
    private boolean confirmationReceivedInd;
    private int confirmationSentCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isOptum;
    private List<ContactRoleAssignmentDTO> contactRoleAssignments;
    private List<PhoneDTO> phones;
}
