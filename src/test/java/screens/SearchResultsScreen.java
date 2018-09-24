package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsScreen extends PhoneLookupScreen {
    @iOSFindBy(xpath = "//XCUIElementTypeTable")
    @AndroidFindBy(id = "product_list_view")
    private MobileElement searchResultsList;

    public SearchResultsScreen(AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public Boolean isSearchResultListPresent(){
        logger.info("Verify results list is displayed");

        try {
            searchResultsList.getId();
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}
