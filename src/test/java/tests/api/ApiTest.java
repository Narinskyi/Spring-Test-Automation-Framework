package tests.api;

import com.onarinskyi._testdata_.api.request.BestSellersRequest;
import com.onarinskyi._testdata_.api.response.bestseller.BestSellersResponse;
import com.onarinskyi._testdata_.reporting.Feature;
import com.onarinskyi.annotations.Request;
import com.onarinskyi.context.AbstractTestNGTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features(Feature.API)
@Stories("BestSellers API")
public class ApiTest extends AbstractTestNGTest {

    @Request
    private BestSellersRequest request;

    @Test
    public void verifyTitles() {
        BestSellersResponse response = request
                .withParameter("title", "1984")
                .sendAndExpect(BestSellersResponse.class);

        softly.assertTrue(response.num_results == 8);

        response.results.forEach(result -> softly.assertTrue(result.title.contains("1984")));

        softly.assertAll();
    }

    @DataProvider
    private Object[][] authors() {
        return new Object[][]{{"Stephen King"}, {"Mark Twain"}, {"Jack London"}};
    }

    @Test(dataProvider = "authors")
    public void verifyAuthors(String author) {
        BestSellersResponse response = request
                .withParameter("author", author)
                .sendAndExpect(BestSellersResponse.class);

        response.results.forEach(result ->
                softly.assertTrue(result.author.contains(author),
                        "Author: " + author + " was not found in the response"));

        softly.assertAll();
    }
}