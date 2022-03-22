import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BaseTest{

    public LoginPageObject(WebDriver driver){

        PageFactory.initElements(driver,this);
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

    @FindBy(linkText = "IceHrm Employee")
    WebElement admintext;

    @FindBy(linkText = "Lala Lamees")
    WebElement managertext;

    @FindBy(linkText = "Sofia O'Sullivan")
    WebElement user1text;

    @FindBy(linkText = "Taylor Holmes")
    WebElement user2text;

    @FindBy(linkText ="Sign out")
    WebElement signoutbtn;

    public void Login(String username, String password){

        System.out.println(username);
        System.out.println(password);
        txt_username.sendKeys(username);
        txt_password.sendKeys(password);
        lgnbtn.click();

    }

    public void Logout(String username){

        if (username.equalsIgnoreCase("admin")) {
            admin.click();
        } else if (username.equalsIgnoreCase("manager")) {
            manager.click();
        } else if (username.equalsIgnoreCase("user1")) {
            user1.click();
        } else if (username.equalsIgnoreCase("user2")) {
            user2.click();
        }

        signoutbtn.click();


    }


}
