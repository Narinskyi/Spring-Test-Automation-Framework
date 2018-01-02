package com.onarinskyi.environment;

import java.util.Arrays;

public enum OperatingSystem {

    WINDOWS,
    MACOS;

    public static OperatingSystem of(String value) {
        return Arrays.stream(OperatingSystem.values())
                .filter(constant -> constant.name().toLowerCase().contains(value))
                .findFirst().orElse(WINDOWS);
    }
}
