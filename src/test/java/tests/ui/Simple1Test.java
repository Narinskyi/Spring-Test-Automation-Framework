package tests.ui;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractUITest;
import com.onarinskyi._testdata_.gui.pages.HomePage;
import org.testng.annotations.Test;

public class Simple1Test extends AbstractUITest {

    @PageObject
    private HomePage page;

    @Test
    public void test() {
        driver.getPageSource();
        page.open();
        page.findDummy();
    }
}
