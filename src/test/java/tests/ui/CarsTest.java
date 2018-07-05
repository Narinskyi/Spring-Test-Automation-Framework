package tests.ui;

import com.onarinskyi._testdata_.gui.pages.CarsPage;
import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.context.AbstractTestNGTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;

@Features("Car rent")
public class CarsTest extends AbstractTestNGTest {

    @PageObject
    private CarsPage onCarsPage;

    @Test
    @TestCaseId("1")
    public void verifySearchByType() {
        onCarsPage.open();

    }
}
