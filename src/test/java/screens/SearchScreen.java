package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class SearchScreen extends PhoneLookupScreen {
    @iOSFindBy(xpath = "(//XCUIElementTypeButton[@name='Search'])[1]")
    @AndroidFindBy(id = "search_search_button")
    private MobileElement searchButton;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name='Item ID or Item Name']")
    @AndroidFindBy(id = "search_item_name_edit")
    private MobileElement itemNameField;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name='Manufacturer']")
    @AndroidFindBy(id = "search_manufacturer_spinner")
    private MobileElement manufacturerField;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='iOS']")
    @AndroidFindBy(id = "search_os_ios_checkbox")
    private MobileElement iOSOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='Android']")
    @AndroidFindBy(id = "search_os_android_checkbox")
    private MobileElement androidOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='Windows']")
    @AndroidFindBy(id = "search_os_blackberry_checkbox")
    private MobileElement windowsOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='BlackBerry']")
    @AndroidFindBy(id = "search_os_windows_checkbox")
    private MobileElement blackBerryOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='All']")
    @AndroidFindBy(id = "search_inventory_all_radio_button")
    private MobileElement allInventoryLevel;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='In Stock']")
    @AndroidFindBy(id = "search_inventory_in_stock_radio_button")
    private MobileElement inStockInventoryLevel;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='Out of Stock']")
    @AndroidFindBy(id = "search_inventory_out_of_stock_radio_button")
    private MobileElement outOfStockInventoryLevel;

    public SearchScreen(AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Verify Search button displayed")
    public Boolean isSearchButtonPresent() {
        if (searchButton.isDisplayed()) {
            return true;
        }

        return false;
    }

    @Step("Enter the Item Name")
    private void enterItemName(String itemNameText) {
        itemNameField.sendKeys(itemNameText);
        if (getPlatform().equalsIgnoreCase("Android")){
            hideKeyboard();
        }
    }

    @Step("Select iOS Operating System")
    private void selectiOSOperatingSystemItem() {
        if (getPlatform().equalsIgnoreCase("IOS")){
            if (iOSOperatingSystemItem.getAttribute("value").equalsIgnoreCase("0")) {
                iOSOperatingSystemItem.click();
            }
        }
        else if (getPlatform().equalsIgnoreCase("ANDROID")){
            if (iOSOperatingSystemItem.getAttribute("checked").equalsIgnoreCase("false")) {
                iOSOperatingSystemItem.click();
            }
        }
    }

    @Step("Select Android Operating System")
    private void selectAndroidOperatingSystemItem() {
        if (getPlatform().toUpperCase().equals("IOS")){
            if (androidOperatingSystemItem.getAttribute("value").equalsIgnoreCase("0")) {
                androidOperatingSystemItem.click();
            }
        }
        else if (getPlatform().equalsIgnoreCase("ANDROID")){
            if (androidOperatingSystemItem.getAttribute("checked").equalsIgnoreCase("false")) {
                androidOperatingSystemItem.click();
            }
        }
    }

    @Step("Select Windows Operating System")
    private void selectWindowsOperatingSystemItem() {
        if(getPlatform().equalsIgnoreCase("IOS")){
            if (windowsOperatingSystemItem.getAttribute("value").equalsIgnoreCase("0")) {
                windowsOperatingSystemItem.click();
            }
        }
        else if (getPlatform().equalsIgnoreCase("ANDROID")){
            if (windowsOperatingSystemItem.getAttribute("checked").equalsIgnoreCase("false")) {
                windowsOperatingSystemItem.click();
            }
        }
    }

    @Step("Select BlackBerry Operating System")
    private void selectBlackBerryOperatingSystemItem() {
        if(getPlatform().equalsIgnoreCase("IOS")){
            if (blackBerryOperatingSystemItem.getAttribute("value").equalsIgnoreCase("0")) {
                blackBerryOperatingSystemItem.click();
            }
        }
        else if (getPlatform().equalsIgnoreCase("ANDROID")){
            if (blackBerryOperatingSystemItem.getAttribute("checked").equalsIgnoreCase("false")) {
                blackBerryOperatingSystemItem.click();
            }
        }
    }

    @Step("Select Manufacturer")
    private void selectManufacturer(String manufacturer){
        if (getPlatform().equals("IOS")) {
            manufacturerField.click();

            MobileElement picker = (MobileElement) driver.findElement(By.className("XCUIElementTypePickerWheel"));

            picker.setValue(manufacturer);

            /*
            //USE THIS CODE IF YOUR PICKER USES VIEWS INSTEAD OF STRINGS
            //SEE https://github.com/appium/appium/issues/6962#issuecomment-264732117
            //
            //Move to the first item Any as the default is Apple and the picker is fixed
            JavascriptExecutor js = driver;
            Map<String, Object> params = new HashMap<>();
            params.put("order", "previous");
            params.put("offset", 0.15);
            params.put("element", picker.getId());
            js.executeScript("mobile: selectPickerWheelValue", params);

            String lastValue = "Any";
            while (!picker.getAttribute("value").equalsIgnoreCase(manufacturer) || !picker.getAttribute("value")
                    .equalsIgnoreCase(lastValue)) {
                params.clear();
                params.put("order", "next");
                params.put("offset", 0.15);
                params.put("element", picker.getId());
                js.executeScript("mobile: selectPickerWheelValue", params);
                lastValue = picker.getAttribute("value");
            }*/

            hideKeyboard();

        }
        else if (getPlatform().equals("ANDROID")) {
            manufacturerField.click();
            MobileElement manufacturerItem = (MobileElement)wait.until(ExpectedConditions.presenceOfElementLocated(By
                    .xpath
                    ("//android.widget.CheckedTextView[@text='" + manufacturer + "']")));

            manufacturerItem.click();
        }
    }

    @Step("Select Inventory Level")
    private void selectInventoryLevel(String inventoryLevel){

       if (inventoryLevel.toUpperCase().equals("ALL")) {
           allInventoryLevel.click();
       }
       else if (inventoryLevel.toUpperCase().equals("IN STOCK")) {
           inStockInventoryLevel.click();
       }
       else if (inventoryLevel.toUpperCase().equals("OUT OF STOCK")) {
           outOfStockInventoryLevel.click();
       }
    }

    @Step("Click Search Button")
    private void clickSearchButton() {
        searchButton.click();
    }


    public void fillSearchForm (String itemName, String manufacturer, Boolean setiOS, Boolean setAndroid, Boolean
            setWindows, Boolean setBlackberry, String inventoryLevel ){
        if (!itemName.isEmpty()){
            enterItemName(itemName);
        }

        if(!manufacturer.isEmpty()){
            selectManufacturer(manufacturer);
        }

        if (setiOS){
            selectiOSOperatingSystemItem();
        }

        if (setAndroid){
            selectAndroidOperatingSystemItem();
        }

        if (setWindows){
            selectWindowsOperatingSystemItem();
        }

        if (setBlackberry){
            selectBlackBerryOperatingSystemItem();
        }

        if (!inventoryLevel.isEmpty()) {
            selectInventoryLevel(inventoryLevel);
        }

        clickSearchButton();
    }
}
