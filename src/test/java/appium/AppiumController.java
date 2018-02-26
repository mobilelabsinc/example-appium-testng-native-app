package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

public class AppiumController {
    protected AppiumDriver driver;
    private DesiredCapabilities capabilities;

    //deviceConnect Information
    public static String server;
    public static String username;
    public static String apiToken;

    //Device Lists
    public static List<Object[]> deviceList;

    //Per Test Capabilities
    public OperatingSystem platform;
    public String udid;
    public String automationName;
    public String bundleID;

    public enum OperatingSystem {
        ANDROID,
        IOS
    }

    public void startAppium() throws Exception {
        switch (platform){
            case IOS:
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("deviceConnectUserName", username);
                capabilities.setCapability("deviceConnectApiKey", apiToken);
                capabilities.setCapability("udid", udid);
                capabilities.setCapability("platformName", platform.toString());
                capabilities.setCapability("bundleID", bundleID);
                capabilities.setCapability("automationName", automationName);

                driver = new IOSDriver<MobileElement>(new URL(server), capabilities);
                break;

            case ANDROID:
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("deviceConnectUserName", username);
                capabilities.setCapability("deviceConnectApiKey", apiToken);
                capabilities.setCapability("udid", udid);
                capabilities.setCapability("platformName", platform.toString());
                capabilities.setCapability("bundleID", bundleID);
                capabilities.setCapability("automationName", automationName);

                driver = new AndroidDriver<MobileElement>(new URL(server), capabilities);
                break;
        }
    }

    public void stopAppium() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
