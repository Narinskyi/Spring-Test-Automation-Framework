package com.onarinskyi.api.request;

import com.onarinskyi.annotations.Get;
import com.onarinskyi.api.core.Request;

@Get("/lists/best-sellers/history.json")
public class BestSellersRequest extends Request {

    public BestSellersRequest() {
        this.parameters.put("api-key", "8aded16392704b52b2af33285cba06a2");
    }
}
