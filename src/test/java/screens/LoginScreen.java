package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginScreen extends PhoneLookupScreen {
    @iOSFindBy(id = "UsernameTextField")
    @AndroidFindBy(id = "com.android.controls:id/usernameEditText")
    private MobileElement usernameField;

    @iOSFindBy(id = "PasswordTextField")
    @AndroidFindBy(id = "com.android.controls:id/passwordEditText")
    private MobileElement passwordField;

    @iOSFindBy(id = "SignInButton")
    @AndroidFindBy(id = "com.android.controls:id/loginButton")
    private MobileElement signInButton;

    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    private void enterUserName(String username){
        usernameField.sendKeys(username);
        hideKeyboard();
    }

    private void enterPassword(String password){
        passwordField.sendKeys(password);
        hideKeyboard();
    }

    private void clickSignIn(){
        signInButton.click();
    }

    public void login (String username, String password) throws Exception {
        enterUserName(username);
        enterPassword(password);
        clickSignIn();
    }
}
