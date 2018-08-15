package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScreen extends PhoneLookupScreen {
    @iOSFindBy(id = "UsernameTextField")
    @AndroidFindBy(id = "login_username_edit")
    private MobileElement usernameField;

    @iOSFindBy(id = "PasswordTextField")
    @AndroidFindBy(id = "login_password_edit")
    private MobileElement passwordField;

    @iOSFindBy(id = "SignInButton")
    @AndroidFindBy(id = "login_sign_in_button")
    private MobileElement signInButton;

    public LoginScreen(AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Enter username")
    private void enterUserName(String username){
        usernameField.sendKeys(username);
        hideKeyboard();
    }

    @Step("Enter password")
    private void enterPassword(String password){
        passwordField.sendKeys(password);
        hideKeyboard();
    }

    @Step("Click SignIn button")
    private void clickSignIn(){
        signInButton.click();
    }


    public void login (String username, String password) throws Exception {
        enterUserName(username);
        enterPassword(password);
        clickSignIn();
    }
}
