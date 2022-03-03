import org.testng.annotations.Test;

public class Assessment1 extends BaseTest {
    @Test
    public void Dashboard() throws InterruptedException {
        PageObjects po=new PageObjects(driver);
        po.Login("admin","admin");
        po.Company();
        po.Logout("admin");

    }
}
