package com.onarinskyi._testdata_.api.request;

import com.onarinskyi.annotations.api.Get;
import com.onarinskyi.api.Request;

@Get(endpoint = "/lists/best-sellers/history.json", api = "nytimes")
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