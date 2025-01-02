package com.optum.ftps.ob.core.contribution.constants;

import lombok.Getter;

@Getter
public enum ContributionTypeCodes {
    TYPE_CODE_001("001"),
    TYPE_CODE_002("002"),
    TYPE_CODE_003("003"),
    TYPE_CODE_004("004"),
    TYPE_CODE_005("005"),
    TYPE_CODE_006("006");

    private final String contributionTypeCode;

    ContributionTypeCodes(String contributionTypeCode) {
        this.contributionTypeCode = contributionTypeCode;
    }
}
