package lesson07.a_refactoring_for_page_factory;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest extends BaseTest{

    MainPage mainPage = new MainPage(webDriver);

    @Test
    public void test_advices(){
        mainPage.visit();
        MainPage mainPage = new MainPage(webDriver);
        mainPage.enterQuery("Dress");
        assertThat(textToBePresentInElement(mainPage.advice, "Dress"));
        mainPage.enterQuery("shirt");
        assertThat(textToBePresentInElement(mainPage.advice, "shirt"));
    }
}
