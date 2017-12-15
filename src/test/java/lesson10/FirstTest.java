package lesson10;

import de.redsix.pdfcompare.PdfComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import utils.CredsHandler;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;


@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(FirstTest.class);
    LoginPage loginPage = new LoginPage(webDriver);

    @Test
    public void test_download() throws Exception {
        loginPage.visit();
        assertThat(titleContains("Login"));
        loginPage.login(CredsHandler.getProperty("email"),CredsHandler.getProperty("passwd"));
        assertThat(titleContains("My account"));

        $(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]")).click();
        assertThat(titleContains("Order history"));

        FileDownloader fileDownloader = new FileDownloader(webDriver);
        fileDownloader.setURI($(By.xpath("//*[@id=\"order-list\"]/tbody/tr/td[6]/a")).getAttribute("href"));
        File secretFile = fileDownloader.downloadFile();
        int httpStatusCode = fileDownloader.getLastDownloadHTTPStatus();

        Assert.assertThat("Check status.",httpStatusCode, is(200));
        //Assert.assertThat("check hash",getFileHash(secretFile, SHA1), CoreMatchers.equalTo("aa784180ef6d7a5990170dbe1f0b65d49b017c76"));
        Assert.assertThat(new PdfComparator(new File("C:\\Users\\vmuser\\Documents\\IN019477.pdf"), secretFile)
                .compare().writeTo("diffOutputPass"), is(true));
    }

    @Test
    public void test_upload() throws InterruptedException {
        open("http://the-internet.herokuapp.com/upload");
        assertThat(titleContains("Internet"));
        $(By.id("file-upload")).sendKeys("C:\\Users\\vmuser\\test.txt");
        $(By.id("file-submit")).click();
        assertThat(textToBePresentInElementLocated(By.tagName("h3"),"File Uploaded!!"));
        Thread.sleep(2000);
    }
}