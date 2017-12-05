package lesson07.b_refactoring_without_page_factory;

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

    MainPage mainPage = new MainPage(webDriver);

    @Test
    public void test_advices(){
        mainPage.visit();
        MainPage mainPage = new MainPage(webDriver);
        mainPage.enterQuery("Dress");
        assertThat(listSizeIs(ADVICE_LOCATOR,7));
        assertThat(textToBePresentInElement($(ADVICE_LOCATOR), "Dress"));
        mainPage.enterQuery("shirt");
        assertThat(listSizeIs(ADVICE_LOCATOR,1));
        assertThat(textToBePresentInElement($(ADVICE_LOCATOR), "shirt"));
    }
}