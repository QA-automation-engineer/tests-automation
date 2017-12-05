package lesson07.b_refactoring_without_page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends SimpleAPI {

    protected WebDriver webDriver;

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
}
