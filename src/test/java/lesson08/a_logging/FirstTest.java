package lesson08.a_logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static lesson07.b_refactoring_without_page_factory.CustomContitions.listSizeIs;
import static lesson07.b_refactoring_without_page_factory.MainPage.ADVICE_LOCATOR;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(FirstTest.class);
    MainPage mainPage = new MainPage(webDriver);
    final String query1 = "Dress";
    final String query2 = "shirt";
    
    @Test
    public void test_advices(){
        LOG.info("Open main page.");
        mainPage.visit();

        LOG.info("Enter query = '" + query1 + "'.");
        mainPage.enterQuery(query1);
        LOG.debug("Waiting for suggestion.");
        assertThat(listSizeIs(ADVICE_LOCATOR,7));
        assertThat(textToBePresentInElement($(ADVICE_LOCATOR), query1));

        LOG.info("Enter query = '" + query2 + "'.");
        mainPage.enterQuery(query2);
        LOG.debug("Waiting for suggestion.");
        assertThat(listSizeIs(ADVICE_LOCATOR,1));
        assertThat(textToBePresentInElement($(ADVICE_LOCATOR), query2));
    }

    @Test
    public void test_warning(){
        open("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        assertThat(ExpectedConditions.titleContains("Login"));
        Logs logs = getWebDriver().manage().logs();
        LogEntries logEntries = logs.get(LogType.BROWSER);

        for (LogEntry logEntry : logEntries) {
            String logMessage = logEntry.getMessage();
            if (logMessage.contains("credit card")) {
                LOG.info(logMessage);
            }
        }
    }
}