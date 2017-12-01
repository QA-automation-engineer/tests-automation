package lesson06.a_implicitly_wait_helps;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;

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
    public void test_openSite(){
        Assert.assertThat("Check site title.", webDriver.getTitle(), containsString("Store1"));
    }
}
