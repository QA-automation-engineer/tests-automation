package lesson06.f_proxy_elements_and_page_factory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

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
        MainPage mainPage = new MainPage();
        mainPage.enterQuery("Dress");
        assertThat(textToBePresentInElement(mainPage.advice, "Dress"));
        mainPage.enterQuery("shirt");
        assertThat(textToBePresentInElement(mainPage.advice, "shirt"));
    }



    class MainPage{
        @FindBy(id = "search_query_top")
        WebElement searchfield;

        @FindBy(xpath = "//*[@id='index']/div[2]/ul/li[1]")
        WebElement advice;

        MainPage(){
            PageFactory.initElements(webDriver, this);
        }
        void enterQuery(String query) {
            searchfield.clear();
            searchfield.sendKeys(query);
        }
    }

    private void assertThat(ExpectedCondition<Boolean> contition) {
        (new WebDriverWait(webDriver,5)).until(contition);
    }
}
