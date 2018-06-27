package com.onarinskyi._testdata_.api.response.bestseller;

import com.onarinskyi.api.Response;

import java.util.ArrayList;
import java.util.List;

public class BestSellersResponse extends Response {
    public int num_results;
    public List<Book> results = new ArrayList<>();
}
