package lesson08.b_event_listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

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
        LOG.debug("Suggestion was appeared.");
        assertThat(textToBePresentInElement($(ADVICE_LOCATOR), query1));

        LOG.info("Enter query = '" + query2 + "'.");
        mainPage.enterQuery(query2);
        LOG.debug("Waiting for suggestion.");
        assertThat(listSizeIs(ADVICE_LOCATOR,1));
        LOG.debug("Suggestion was appeared.");
        assertThat(textToBePresentInElement($(ADVICE_LOCATOR), query2));
    }
}