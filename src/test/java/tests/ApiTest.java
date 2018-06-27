package tests;

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
    public void test() {
        BestSellersResponse response = request
                .withParameter("api-key", "8aded16392704b52b2af33285cba06a2")
                .withParameter("title", "1984")
                .sendAndExpect(BestSellersResponse.class);

        softly.assertTrue(response.num_results == 8);
        softly.assertTrue(response.results.get(0).title.contains("1984"));
        softly.assertAll();
    }
}
