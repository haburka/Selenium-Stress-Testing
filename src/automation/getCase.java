package automation;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class getCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://realcrimetime.com/api/doc";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(baseUrl);
  }
  /*
   * This clicks on the correct thing
   * And the clicks the get path
   * I assert that there is an ok in the path
   * Sleeps are needed!
   * This test that the database is returing a 200 OK, which means the DB
   * Is still functional.
   */
  @Test
  public void testPost() throws Exception {
    driver.findElement(By.cssSelector("#get--MapPoints > div.heading.toggler > h3 > span.path")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//li[@id='get--MapPoints']/div[2]/ul/li[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("(//input[@value='Try!'])[2]")).click();
    Thread.sleep(1000);
    String result = driver.findElement(By.xpath("//li[@id='get--MapPoints']/div[2]/div/div[2]/div/pre[2]")).getText();
    assertEquals("200 OK", result.substring(0, 6));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
