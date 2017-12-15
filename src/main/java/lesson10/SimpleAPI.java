package lesson10;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public abstract class SimpleAPI {

    public abstract WebDriver getWebDriver();

    WebElement $(By locator){
        return assertThat(ExpectedConditions.presenceOfElementLocated(locator));
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

    protected void open(String url){
        getWebDriver().get(url);
    }

    protected <V> V assertThat(Function<? super WebDriver, V> contition) {
        return (new WebDriverWait(getWebDriver(),5)).until(contition);
    }

    static {
        if(System.getProperty("report.path") == null){
            Date dateNow = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            System.setProperty("report.path", "./reports/IDE-test-build-" + format.format(dateNow));
        }
    }
}
