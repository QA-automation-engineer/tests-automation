package lesson07.a_refactoring_for_page_factory;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public abstract class SimpleAPI {

    public abstract WebDriver getWebDriver();

    protected void open(String url){
        getWebDriver().get(url);
    }

    WebElement $(By locator){
        return assertThat(presenceOfElementLocated(locator));
    }

    WebElement $(String xpath){
        return $(By.xpath(xpath));
    }

    List<WebElement> $$(By locator){
        return assertThat(presenceOfAllElementsLocatedBy(locator));
    }

    List<WebElement> $$(String xpath){
        return getWebDriver().findElements(By.xpath(xpath));
    }

    protected void assertThat(ExpectedCondition<Boolean> condition) {
        (new WebDriverWait(getWebDriver(),5)).until(condition);
    }

    protected <V> V assertThat(Function<? super WebDriver, V> contition) {
        return (new WebDriverWait(getWebDriver(),5)).until(contition);
    }

    protected <V> V assertThat(Function<? super WebDriver, V> contition, int timeOut) {
        return (new WebDriverWait(getWebDriver(),timeOut)).until(contition);
    }

}
