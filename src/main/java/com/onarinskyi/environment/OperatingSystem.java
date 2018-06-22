package com.onarinskyi.environment;

import java.util.Arrays;

public enum OperatingSystem {

    WINDOWS,
    MACOS;

    public static OperatingSystem current() {
        final String os = System.getProperty("os.name");

        return Arrays.stream(OperatingSystem.values())
                .filter(constant -> constant.name().toLowerCase()
                        .contains(os.substring(0, os.indexOf(" ")).toLowerCase()))
                .findFirst().orElse(WINDOWS);
    }
}
