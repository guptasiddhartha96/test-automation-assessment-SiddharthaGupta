import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
