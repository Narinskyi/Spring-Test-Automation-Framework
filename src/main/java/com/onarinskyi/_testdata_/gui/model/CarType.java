package com.onarinskyi._testdata_.gui.model;

import java.util.Arrays;

public enum CarType {
    STANDARD("Standard"), LUXURY("Luxury");

    private String displayedText;

    CarType(String displayedText) {
        this.displayedText = displayedText;
    }

    public String getDisplayedText() {
        return displayedText;
    }

    public String[] getDisplayedValues() {
        return Arrays.stream(values()).map(v -> v.displayedText).toArray(String[]::new);
    }
}
