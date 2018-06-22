package com.onarinskyi.environment;

import java.util.Arrays;

public enum Browser {

    CHROME,
    IE,
    FIREFOX,
    MOBILE_EMULATOR_CHROME,
    TABLET_EMULATOR_CHROME;

    public static Browser of(String type) {
        return Arrays.stream(Browser.values())
                .filter(constant -> constant.name().contains(type))
                .findFirst().orElse(CHROME);
    }
}
