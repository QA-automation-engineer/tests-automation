package lesson06.d_refactor_waits;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest {

    static final String BASE_URL = "http://automationpractice.com/index.php";
    static WebDriver webDriver;

    @BeforeClass
    public static void setUp(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }

    @AfterClass
    public static void tearDown(){
        webDriver.quit();
        webDriver = null;
    }

    @Test
    public void test_advices(){
        WebElement searchfield = webDriver.findElement(By.id("search_query_top"));
        searchfield.sendKeys("Dress");
        assertThat(textToBePresentInElementLocated(By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]"), "Dress"));
        searchfield.clear();
        searchfield.sendKeys("shirt");
        assertThat(textToBePresentInElementLocated(By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]"), "shirt"));
    }

    private void assertThat(ExpectedCondition<Boolean> contition) {
        (new WebDriverWait(webDriver,5)).until(contition);
    }
}
