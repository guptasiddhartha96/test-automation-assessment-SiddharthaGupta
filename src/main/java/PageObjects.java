import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjects extends BaseTest {
    public PageObjects(WebDriver driver){

        PageFactory.initElements(driver, this);
    }
    @FindBy(id="username")
    WebElement txt_username;

    @FindBy(id="password")
    WebElement txt_password;

    @FindBy(xpath="//button[contains(@class,'btn btn-info btn-medium w-100 rounded-5 text-uppercase') and text()='Log in ']")
    WebElement lgnbtn;

    @FindBy(xpath = "//*[@id=\"delegationDiv\"]/nav/div[2]/ul/li[5]/a/span")
    WebElement admin;

    @FindBy(xpath="//span[contains(text(),'Lala')]")
    WebElement manager;

    @FindBy(xpath="//span[contains(text(),'Sofia')]")
    WebElement user1;

    @FindBy(xpath="//span[contains(text(),'Taylor ')]")
    WebElement user2;

    @FindBy(linkText ="Sign out")
    WebElement signoutbtn;
    //company link
    @FindBy(id="companyLink")
    WebElement cmp;

    @FindBy(xpath="//*[@id=\"CompanyStructureTable\"]/div/div/div[1]/div[1]/div/div/button/span[2]")
    WebElement add;

    @FindBy(id="title")
    WebElement name;

    @FindBy(id="description")
    WebElement desc;

    @FindBy(xpath="//*[@id=\"rc_select_0\"]")
    WebElement type;

    @FindBy(xpath="//*[@id=\"rc_select_1\"]")
    WebElement country;

    @FindBy(xpath="//*[@id=\"rc_select_2\"]")
    WebElement time;

    @FindBy(xpath="//span[text()='Save']")
    WebElement save;

    public void Login(String username, String password){
        System.out.println(username);
        System.out.println(password);
        txt_username.sendKeys(username);
        txt_password.sendKeys(password);
        lgnbtn.click();
    }
    public void Logout(String username){

        if(username.equalsIgnoreCase("admin")){
            admin.click();
        }else if (username.equalsIgnoreCase("manager")){
            manager.click();
        }else if (username.equalsIgnoreCase("user1")){
            user1.click();
        }else if(username.equalsIgnoreCase("user2")){
            user2.click();
        }

        signoutbtn.click();
    }

    public void Company() throws InterruptedException {
        cmp.click();
        Thread.sleep(2000);
        add.click();
        Thread.sleep(2000);
        name.sendKeys("Siddhartha");
        Thread.sleep(2000);
        desc.sendKeys("Automation test");
        Thread.sleep(2000);
        type.sendKeys("Company");
        Thread.sleep(2000);
        type.sendKeys(Keys.ENTER);
        country.sendKeys("India");
        Thread.sleep(2000);
        country.sendKeys(Keys.ENTER);
        time.sendKeys("(GMT 00:00) Africa/Abidjan");
        Thread.sleep(2000);
        time.sendKeys(Keys.ENTER);
        save.click();



    }




}
