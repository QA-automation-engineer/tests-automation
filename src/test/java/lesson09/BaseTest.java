package lesson09;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest extends SimpleAPI {

    static WebDriver webDriver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        if ("false".equals(System.getProperty("isRemote"))){
            webDriver = new ChromeDriver();
        } else {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            webDriver = new RemoteWebDriver(new URL("http://169.254.21.186:4444/wd/hub"), cap);
        }
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown(){
        webDriver.quit();
        webDriver = null;
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    static {
        if(System.getProperty("report.path") == null){
            Date dateNow = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            System.setProperty("report.path", "./reports/IDE-test-build-" + format.format(dateNow));
        }
    }
}
