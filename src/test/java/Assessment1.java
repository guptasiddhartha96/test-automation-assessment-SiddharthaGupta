import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Assessment1 extends BaseTest {
    @Test
    public void Dashboard() throws InterruptedException, IOException {
        try {
            PageObjects po = new PageObjects(driver);
            prop = new Properties();
            FileInputStream fis = new FileInputStream("C:\\Users\\siddharthagupta\\IdeaProjects\\maven-demo\\src\\test\\resources\\config.properties");
            prop.load(fis);
            po.Login(prop.getProperty("username"), prop.getProperty("password"));
            po.Company();
            po.Logout(prop.getProperty("username"));
        } catch (Exception e){
            exitOnFailure(e);
        }



    }
}
