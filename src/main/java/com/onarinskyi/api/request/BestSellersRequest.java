package com.onarinskyi.api.request;

import com.onarinskyi.annotations.Get;
import com.onarinskyi.annotations.Url;
import com.onarinskyi.api.core.Request;

@Get
@Url("lists/best-sellers/history.json")
public class BestSellersRequest extends Request {
}
