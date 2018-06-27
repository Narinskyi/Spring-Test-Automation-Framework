package tests.api;

import com.onarinskyi.annotations.Request;
import com.onarinskyi.api.request.BestSellersRequest;
import com.onarinskyi.api.response.bestseller.BestSellersResponse;
import com.onarinskyi.core.AbstractTestNGTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Api")
@Stories("Languages")
public class ApiTest extends AbstractTestNGTest {

    @Request
    private BestSellersRequest request;

    @Test
    public void verifyApi() {
        BestSellersResponse response = request
                .withNumber(1)
                .withString("2")
                .withParameter("title", "1984")
                .sendAndExpect(BestSellersResponse.class);

        softly.assertTrue(response.num_results == 8);

        response.results.forEach(result -> softly.assertTrue(result.title.contains("1984")));

        softly.assertAll();
    }
}