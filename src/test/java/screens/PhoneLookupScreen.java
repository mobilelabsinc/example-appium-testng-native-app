package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class PhoneLookupScreen {

    protected final AppiumDriver driver;
    protected final WebDriverWait wait;

    public PhoneLookupScreen(AppiumDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    public String getPlatform(){
        return driver.getCapabilities().getCapability("platform").toString().toUpperCase();
    }

    public void hideKeyboard() {
        try{
            if (getPlatform().equals("IOS")) {
                MobileElement keyboardDone = (MobileElement) driver.findElement(By.xpath("//*[@name='Done']"));
                keyboardDone.click();
            }
            else if (getPlatform().toUpperCase().equals("ANDROID")) {
                driver.hideKeyboard();
            }
        }
        catch (Exception ex){
            //do nothing
        }
    }
}
