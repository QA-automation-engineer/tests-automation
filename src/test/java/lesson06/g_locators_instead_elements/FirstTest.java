package lesson06.g_locators_instead_elements;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest {

    static final String BASE_URL = "http://automationpractice.com/index.php";
    static WebDriver webDriver;

    @BeforeClass
    public static void setUp(){
        webDriver = new ChromeDriver();
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        MainPage mainPage = new MainPage(webDriver);
        mainPage.enterQuery("Dress");
        assertThat(textToBePresentInElementLocated(mainPage.ADVICE_LOCATOR, "Dress"));
        mainPage.enterQuery("shirt");
        assertThat(textToBePresentInElementLocated(mainPage.ADVICE_LOCATOR, "shirt"));
    }


    class MainPage{
        public final By SEARCH_FIELD_LOCATOR = By.id("search_query_top");
        public final By ADVICE_LOCATOR = By.xpath("//*[@id='index']/div[2]/ul/li[1]");

        private WebDriver webDriver;

        MainPage(WebDriver webDriver){
            this.webDriver = webDriver;
        }

        void enterQuery(String query) {
            webDriver.findElement(SEARCH_FIELD_LOCATOR).clear();
            webDriver.findElement(SEARCH_FIELD_LOCATOR).sendKeys(query);
        }
    }

    private void assertThat(ExpectedCondition<Boolean> contition) {
        (new WebDriverWait(webDriver,5)).until(contition);
    }
}
