package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class SearchScreen extends PhoneLookupScreen {
    @iOSFindBy(xpath = "(//XCUIElementTypeButton[@name='Search'])[1]")
    @AndroidFindBy(id = "com.android.controls:id/searchButton")
    private MobileElement searchButton;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name='Item ID or Item Name']")
    @AndroidFindBy(id = "com.android.controls:id/criteria1EditText")
    private MobileElement itemNameField;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name='Manufacturer']")
    @AndroidFindBy(id = "com.android.controls:id/searchSpinner")
    private MobileElement manufacturerField;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='iOS']")
    @AndroidFindBy(id = "com.android.controls:id/criteria3RadioButton3")
    private MobileElement iOSOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='Android']")
    @AndroidFindBy(id = "com.android.controls:id/criteria3RadioButton1")
    private MobileElement androidOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='Windows']")
    @AndroidFindBy(id = "com.android.controls:id/criteria3RadioButton2")
    private MobileElement windowsOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeSwitch[@label='BlackBerry']")
    @AndroidFindBy(id = "com.android.controls:id/criteria3RadioButton4")
    private MobileElement blackBerryOperatingSystemItem;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='All']")
    @AndroidFindBy(id = "com.android.controls:id/criteria4RadioButton3")
    private MobileElement allInventoryLevel;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='In Stock']")
    @AndroidFindBy(id = "com.android.controls:id/criteria4RadioButton1")
    private MobileElement inStockInventoryLevel;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='Out of Stock']")
    @AndroidFindBy(id = "com.android.controls:id/criteria4RadioButton2")
    private MobileElement outOfStockInventoryLevel;

    public SearchScreen(AppiumDriver driver) {
        super(driver);
    }

    public Boolean isSearchButtonPresent() {
        if (searchButton.isDisplayed()) {
            return true;
        }

        return false;
    }

    private void enterItemName(String itemNameText) {
        itemNameField.sendKeys(itemNameText);
        if (getPlatform().equalsIgnoreCase("Android")){
            hideKeyboard();
        }
    }

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

    private void selectManufacturer(String manufacturer){
        if (getPlatform().equals("IOS")) {
            manufacturerField.click();

            MobileElement picker = (MobileElement) driver.findElement(By.className("XCUIElementTypePickerWheel"));

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
            }

            hideKeyboard();

        }
        else if (getPlatform().equals("ANDROID")) {
            manufacturerField.click();
            driver.findElementByXPath(".//*[@text='" + manufacturer + "']").click();
        }
    }

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

        searchButton.click();
    }
}
