package automation;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;


public class PostCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	DesiredCapabilities capability = DesiredCapabilities.firefox();
	driver = new RemoteWebDriver(new URL("http://192.168.1.9:4444/wd/hub"), capability);
    baseUrl = "http://realcrimetime.com/api/doc";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(baseUrl);
  }
/*
 * This sends in some data to the DB
 * We check to see that the post test returns an OK error code
 */
  @Test
  public void testPostCase() throws Exception {
	
    driver.findElement(By.xpath("//li[@id='post--map_point-{info}-{lat}-{lng}']/div")).click();
    
    driver.findElement(By.xpath("//li[@id='post--map_point-{info}-{lat}-{lng}']/div[2]/ul/li[2]")).click();
    
    driver.findElement(By.xpath("(//input[@type='text'])[16]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[16]")).sendKeys("selenium est");
    driver.findElement(By.xpath("(//input[@type='text'])[18]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[18]")).sendKeys("15");
    driver.findElement(By.xpath("(//input[@type='text'])[20]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[20]")).sendKeys("16");
    driver.findElement(By.xpath("(//input[@value='Try!'])[3]")).click();
    
    String result = driver.findElement(By.xpath("//li[@id='post--map_point-{info}-{lat}-{lng}']/div[2]/div/div[2]/div/pre[2]")).getText();
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
  /*
   * This clicks on the correct thing
   * And the clicks the get path
   * I assert that there is an ok in the path
   * Sleeps are needed!
   * This test that the database is returing a 200 OK, which means the DB
   * Is still functional.
   */
  @Test
  public void testGet() throws Exception {
	
    driver.findElement(By.cssSelector("#get--MapPoints > div.heading.toggler")).click();
    
    driver.findElement(By.xpath("//li[@id='get--MapPoints']/div[2]/ul/li[2]")).click();
    
    driver.findElement(By.xpath("(//input[@value='Try!'])[2]")).click();
    
    String result = driver.findElement(By.xpath("//li[@id='get--MapPoints']/div[2]/div/div[2]/div/pre[2]")).getText();
    assertEquals("200 OK", result.substring(0, 6));
  }
}
