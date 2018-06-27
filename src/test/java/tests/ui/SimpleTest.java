package tests.ui;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractUITest;
import com.onarinskyi.gui.pages.HomePage;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Simple")
@Stories("Test")
public class SimpleTest extends AbstractUITest {

    @PageObject
    private HomePage page;

    @Test
    public void test() {
        page.open();
        page.findDummy();
    }
}
