import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import static org.awaitility.Awaitility.await;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class BaseTest {
    protected int timeToWaitInSecs = 50;
    public WebDriver driver;
    public Properties prop;
    WebDriverWait wait;

    @BeforeClass
    public WebDriver setup() throws IOException {
        prop=new Properties();
        FileInputStream fis=new FileInputStream("C:\\Users\\siddharthagupta\\IdeaProjects\\maven-demo\\src\\test\\resources\\config.properties");
        prop.load(fis);
        String browserName=prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("ie")){
            WebDriverManager.iedriver().setup();
            driver=new InternetExplorerDriver();
        }
        int implicitWait=Integer.valueOf(prop.getProperty("implicitwait"));
        System.out.println(Integer.valueOf(prop.getProperty("implicitwait")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        System.out.println(prop.getProperty("url"));
        return driver;

    }

    // Test method to take screenshot
    public void exitOnFailure(Exception e){
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File("./FailedTestcaseScreenshots/failedTestcaseScreenshot.png"));
            String fileName = System.getProperty("user.dir")
                    + "/FailedTestcaseScreenshots/failedTestcaseScreenshot.png";

            byte[] fileContent = FileUtils.readFileToByteArray(new File(fileName));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            String path = "<img src=\"data:image/png;base64, " + encodedString + "\" />";
            Assert.assertTrue(1 == 2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
    protected void waitForElementToBePresent(WebElement element) {
        await().ignoreExceptions().atMost(timeToWaitInSecs, TimeUnit.SECONDS).until(() -> isElementDisplayed(element));
    }

    // Test method to add wait for Element with expected condition
    protected boolean waitForElementWithCondition(ExpectedCondition<?> e) throws TimeoutException {
        wait.until(e);
        return true;
    }

    protected void waitForElementToBeVisible(WebElement element) {
        waitForElementWithCondition(ExpectedConditions.visibilityOf(element));
    }
    protected boolean isElementDisplayed(WebElement element) {
        boolean status = false;
        try {
            if (element.isEnabled()) {
                if (element.isDisplayed()) {
                    status = true;
                }
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
        }
        return status;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
