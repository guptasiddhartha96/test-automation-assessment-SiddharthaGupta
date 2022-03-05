import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;

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
            Reporter.setEscapeHtml(false);

            byte[] fileContent = FileUtils.readFileToByteArray(new File(fileName));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            String path = "<img src=\"data:image/png;base64, " + encodedString + "\" />";
            Reporter.log(path);
            Assert.assertTrue(1 == 2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
