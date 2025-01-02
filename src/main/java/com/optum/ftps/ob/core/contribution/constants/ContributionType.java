package com.optum.ftps.ob.core.contribution.constants;

import lombok.Getter;

@Getter
public enum ContributionType {
    CURRENT_YEAR("CURRENT YEAR"),
    PARTICIPANT("PARTICIPANT"),
    EMPLOYER("EMPLOYER"),
    PREVIOUS_YEAR("PREVIOUS YEAR"),
    INVALID_CONTRIBUTION("INVALID"),
    ROLLOVER_CONTRIBUTION("ROLLOVER"),
    CURRENT_YEAR_PARTICIPANT("Current Year Participant"),
    CURRENT_YEAR_EMPLOYER("Current Year Employer"),
    PREVIOUS_YEAR_PARTICIPANT("Previous Year Participant"),
    PREVIOUS_YEAR_EMPLOYER("Previous Year Employer"),
    ROLLOVER("Rollover"),
    INVALID_CONTRIBUTION_TYPE("Invalid Contribution Type");

    private final String contributionTypeName;

    ContributionType(String contributionTypeName) {
        this.contributionTypeName = contributionTypeName;
    }
}
