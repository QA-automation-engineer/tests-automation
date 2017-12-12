package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import utils.CredsHandler;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;


@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(FirstTest.class);
    LoginPage loginPage = new LoginPage(webDriver);

    @Test
    public void test_advices(){
        loginPage.visit();
        assertThat(titleContains("Login"));

        loginPage.login(CredsHandler.getProperty("email"),CredsHandler.getProperty("passwd"));
        assertThat(titleContains("My account"));
        Set<Cookie> cookies = webDriver.manage().getCookies();

        $(By.className("logout")).click();
        assertThat(titleContains("Login"));

        for(Cookie cookie : cookies){
            if (cookie.getName().contains("PrestaShop")) {
                webDriver.manage().addCookie(cookie);
            } else {
                LOG.info("Oops... ");
            }
        }
        webDriver.navigate().refresh();
        assertThat(titleContains("My account"));
    }
}