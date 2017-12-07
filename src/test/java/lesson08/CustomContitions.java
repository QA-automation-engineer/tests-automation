package lesson08;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;
import java.util.List;

public class CustomContitions {

    public static ExpectedCondition<List<WebElement>> listSizeIs(By locator, int minSize){
        return new ExpectedCondition<List<WebElement>>() {
            @Nullable
            @Override
            public List<WebElement> apply(@Nullable WebDriver webDriver) {
                List<WebElement> list = webDriver.findElements(locator);
                return list.size() == minSize ? list : null;
            }
        };
    }
}
