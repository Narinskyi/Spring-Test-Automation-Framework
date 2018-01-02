package com.onarinskyi.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Timeout {

    private long implicitWait;
    private long explicitWait;

    public Timeout(@Value("${implicit.wait}") String implicitWait, @Value("${explicit.wait}") String explicitWait) {
        this.implicitWait = Long.valueOf(implicitWait);
        this.explicitWait = Long.valueOf(explicitWait);
    }

    public long implicitWait() {
        return implicitWait;
    }

    public long explicitWait() {
        return explicitWait;
    }
}
