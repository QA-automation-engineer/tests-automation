package lesson07.b_refactoring_without_page_factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;
import java.util.List;

public class CustomContitions {

    private static final Logger LOG = LogManager.getLogger(CustomContitions.class);

    public static ExpectedCondition<List<WebElement>> listSizeIs(By locator, int minSize){
        return new ExpectedCondition<List<WebElement>>() {
            @Nullable
            @Override
            public List<WebElement> apply(@Nullable WebDriver webDriver) {
                int i = 0;
                List<WebElement> list = webDriver.findElements(locator);
                int size = list.size();
                LOG.debug("Iteration No: " + (++i) + ", size is: " + size + ".");
                return size == minSize ? list : null;
            }
        };
    }
}
