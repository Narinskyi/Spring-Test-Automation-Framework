package com.onarinskyi.api.response.bestseller;

import com.onarinskyi.api.core.Response;

import java.util.ArrayList;
import java.util.List;

public class BestSellersResponse extends Response {
    public int num_results;
    public List<Book> results = new ArrayList<>();
}
