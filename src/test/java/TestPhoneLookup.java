import appium.AppiumController;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.*;


import org.testng.xml.XmlSuite;
import rp.com.google.common.io.BaseEncoding;
import screens.LoginScreen;
import screens.PhoneLookupScreen;
import screens.SearchResultsScreen;
import screens.SearchScreen;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class TestPhoneLookup extends AppiumController {
    protected static Logger logger = LogManager.getLogger(TestPhoneLookup.class);

    protected LoginScreen loginScreen;
    protected SearchScreen searchScreen;
    protected SearchResultsScreen searchResultsScreen;

    @BeforeClass
    @Parameters({"gigafoxUrl", "gigafoxUser", "gigafoxKey", "iosBundleId", "androidBundleId", "deviceId", "deviceOs"})
    public void setUp(String gigafoxUrl, String gigafoxUser, String gigafoxKey, String iosBundleId, String androidBundleId, String deviceId, String deviceOs) throws Exception {

        platform = OperatingSystem.valueOf(deviceOs.toUpperCase());
        server = gigafoxUrl;
        username = gigafoxUser;
        apiToken = gigafoxKey;

        if(platform.equals(OperatingSystem.ANDROID)){
            udid = deviceId;
            bundleID = androidBundleId;
            automationName = "UiAutomator2";
        }
        else if (platform.equals(OperatingSystem.IOS)) {
            udid = deviceId;
            bundleID = iosBundleId;
            automationName = "XCUITest";
        }

        startAppium();

        loginScreen = new LoginScreen(driver, wait);
        searchScreen = new SearchScreen(driver, wait);
        searchResultsScreen = new SearchResultsScreen(driver, wait);
    }

    @Test
    @Feature("Login")
    @Story("Valid Login")
    @Description("Verifies that the Search Button appears on the Search Screen after entering the username and password and then clicking the Sign In button on the login screen")
    public void loginTest() throws Exception {

        try {
            loginScreen.login("mobilelabs", "demo");
            Assert.assertTrue(searchScreen.isSearchButtonPresent());
            getScreenshot("Search Screen");
        } catch (Exception ex) {

            //Get screenshot if test fails
            getScreenshot("Failed - Exception");
            throw ex;
        }
    }

    @Test
    @Feature("Search")
    @Story("Valid Search")
    @Description("Verifies that the list of items is returned after filling out the search form")
    public void searchTest() throws Exception {

        try {
            searchScreen.fillSearchForm("Droid Charge", "Samsung", true, true, false, false, "In Stock");
            Assert.assertTrue(searchResultsScreen.isSearchResultListPresent());
            getScreenshot("Search Results");
        } catch (Exception ex) {

            //Get screenshot if test fails
            getScreenshot("Failed - Exception");
            throw ex;
        }
    }

    @AfterClass
    public void tearDown() throws Exception {

        stopAppium();
    }

    @Attachment(value = "{attachmentName}", type = "image/png")
    public byte[] getScreenshot(String attachmentName) throws Exception {
        // make screenshot and get is as base64
        byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);

        logger.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(screenshot), attachmentName);

        return screenshot;
    }


}