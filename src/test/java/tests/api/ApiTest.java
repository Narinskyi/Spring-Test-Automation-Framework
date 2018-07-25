package tests.api;

import com.onarinskyi._testdata_.reporting.Feature;
import com.onarinskyi.annotations.Request;
import com.onarinskyi._testdata_.api.request.BestSellersRequest;
import com.onarinskyi._testdata_.api.response.bestseller.BestSellersResponse;
import com.onarinskyi.context.AbstractTestNGTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features(Feature.API)
@Stories("BestSellers API")
public class ApiTest extends AbstractTestNGTest {

    @Request
    private BestSellersRequest request;

    @Test
    public void verifyApi() {
        BestSellersResponse response = request
                .withParameter("title", "1984")
                .sendAndExpect(BestSellersResponse.class);

        softly.assertTrue(response.num_results == 8);

        response.results.forEach(result -> softly.assertTrue(result.title.contains("1984")));

        softly.assertAll();
    }
}