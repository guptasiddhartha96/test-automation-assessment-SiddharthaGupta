import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DashboardCompany extends BaseTest {
    @Test
    public void Dashboard() throws InterruptedException, IOException {
        try {
            CompanyPageObjects po = new CompanyPageObjects(driver);
            LoginPageObject lpo=new LoginPageObject(driver);
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
            prop.load(fis);
            lpo.Login(prop.getProperty("username"), prop.getProperty("password"));
            po.Company();
            lpo.Logout(prop.getProperty("username"));
        } catch (Exception e){
            e.printStackTrace();
            exitOnFailure(e);
        }



    }
}
