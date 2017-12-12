package lesson09;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    static final String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    public static final By EMAIL_FIELD_LOCATOR = By.id("email");
    public static final By PASSWORD_FIELD_LOCATOR = By.id("passwd");
    public static final By SIGNIN_BTN_LOCATOR = By.id("SubmitLogin");


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    void visit(){
        open(LOGIN_URL);
    }

    void login(String email, String password) {
        setValue($(EMAIL_FIELD_LOCATOR),email);
        setValue($(PASSWORD_FIELD_LOCATOR),password);
        $(SIGNIN_BTN_LOCATOR).click();
    }

    private void setValue(WebElement input, String value){
        input.click();
        input.clear();
        input.sendKeys(value);
    }

}
