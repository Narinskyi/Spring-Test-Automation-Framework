package com.onarinskyi.environment;

import java.util.Arrays;

public enum OperatingSystem {

    WINDOWS,
    MACOS;

    public static OperatingSystem of(String type) {
        return Arrays.stream(OperatingSystem.values())
                .filter(constant -> constant.name().toLowerCase().contains(type))
                .findFirst().orElse(WINDOWS);
    }
}
