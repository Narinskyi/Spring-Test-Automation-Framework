package com.onarinskyi.api.request;

import com.onarinskyi.annotations.Get;
import com.onarinskyi.api.core.Request;

@Get("/lists/best-sellers/history.json")
public class BestSellersRequest extends Request {

    public int number;
    public String string;

    public BestSellersRequest withNumber(int number) {
        this.number = number;
        return this;
    }

    public BestSellersRequest withString(String string) {
        this.string = string;
        return this;
    }

    public BestSellersRequest() {
        this.parameters.put("api-key", "8aded16392704b52b2af33285cba06a2");
    }
}
