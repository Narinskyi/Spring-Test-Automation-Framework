package com.onarinskyi.environment;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public enum BrowserType {

    FIREFOX,
    CHROME,
    MOBILE_EMULATOR_CHROME,
    TABLET_EMULATOR_CHROME;

    public static BrowserType of(@Value("${browser.type}") String value) {
        return Arrays.stream(BrowserType.values())
                .filter(constant -> constant.name().contains(value))
                .findFirst().orElse(CHROME);
    }
}
