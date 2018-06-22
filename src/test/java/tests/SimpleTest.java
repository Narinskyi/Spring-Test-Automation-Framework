package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTestNGTest;
import org.testng.annotations.Test;
import com.onarinskyi.gui.pages.HomePage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Simple")
@Stories("Test")
public class SimpleTest extends AbstractTestNGTest {

    @PageObject
    private HomePage page;

    @Test
    public void test() {
        page.open();
        page.findDummy();
    }
}
