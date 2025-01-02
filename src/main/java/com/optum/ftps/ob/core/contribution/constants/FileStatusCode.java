package com.optum.ftps.ob.core.contribution.constants;

import com.optum.ftps.ob.core.contribution.util.StringUtil;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum FileStatusCode {
    DENIED("D"),
    APPROVED("A");

    private static final Map<String, FileStatusCode> NAME_MAP = new HashMap<>();

    static {
        for (FileStatusCode status : FileStatusCode.values()) {
            NAME_MAP.put(status.statusCode, status);
        }
    }

    private final String statusCode;

    FileStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public static FileStatusCode byCode(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        return NAME_MAP.get(code.trim().toUpperCase());
    }
}
