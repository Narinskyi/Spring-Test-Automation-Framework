package test_data.gui.pages;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.core.AbstractPage;
import test_data.model.Category;
import test_data.gui.components.Header;

public class BasePage extends AbstractPage {

    @PageComponent
    private Header header;

    public void searchFor(String request) {
        header.searchFor(request);
    }

    public void searchFor(String request, Category inCategory) {
        header.searchFor(request);
    }
}
