import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class CompanyPageObjects extends BaseTest {
    public CompanyPageObjects(WebDriver driver){
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

    @FindBy(linkText ="Sign out")
    WebElement signoutbtn;
    //company link
    @FindBy(id="companyLink")
    WebElement cmp;

    @FindBy(xpath="//button[@class='ant-btn ant-btn-primary']")
    WebElement add;

    @FindBy(id="title")
    WebElement name;

    @FindBy(id="description")
    WebElement desc;

    @FindBy(xpath="//input[@id='rc_select_0']")
    WebElement type;

    @FindBy(xpath="(//input[@type='search'])[2]")
    WebElement country;

    @FindAll(@FindBy(xpath="//div[@class='rc-virtual-list-holder-inner']"))
    List<WebElement> countryOptions;

    @FindBy(xpath="//input[@id='rc_select_2' ]")
    WebElement time;

    @FindBy(xpath="//span[text()='Save']")
    WebElement save;

    @FindBy(id="advanced_search_searchTerm")
    WebElement inputsearchtext;

    @FindBy(xpath="//button[@type='button' and @class='ant-btn ant-btn-primary ant-input-search-button' ]")
    WebElement btnsearch;

    @FindAll(@FindBy(xpath="//*[@id=\"CompanyStructureTable\"]/div/div/div[2]/div/div/div/div/div/div/div/table/tbody/tr[1]/td"))
    List<WebElement> cols;

    @FindAll(@FindBy(xpath="//*[@id=\"CompanyStructureTable\"]/div/div/div[2]/div/div/div/div/div/div/div/table/tbody/tr"))
    List<WebElement> rows;

    @FindBy(xpath="//tbody/tr[1]/td[1]")
    WebElement nameelement;

    @FindBy(xpath="//tbody/tr[1]/td[3]")
    WebElement typeelement;

    @FindBy(xpath="//tbody/tr[1]/td[4]")
    WebElement countryelement;

    public void Company() throws InterruptedException, IOException {
        try{
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
            prop.load(fis);
            cmp.click();
            Thread.sleep(5000);
            add.click();
            name.sendKeys(prop.getProperty("name"));
            desc.sendKeys(prop.getProperty("details"));
            type.sendKeys(prop.getProperty("type"));
            type.sendKeys(Keys.ENTER);
            country.click();
            country.sendKeys(prop.getProperty("country"));
            waitForElementToBePresent(country);
            for(WebElement option:countryOptions){
                System.out.println(option.getText());
                if(option.getText().equalsIgnoreCase(prop.getProperty("country"))){
                    option.click();
                    break;
                }
            }
            country.sendKeys(Keys.ENTER);
            time.sendKeys(prop.getProperty("time"));
            time.sendKeys(Keys.ENTER);
            save.click();
            inputsearchtext.sendKeys(prop.getProperty("name"));
            btnsearch.click();
            waitForElementToBePresent(nameelement);
            waitForElementToBePresent(typeelement);
            waitForElementToBePresent(countryelement);
            System.out.println(nameelement.getText());
            System.out.println(typeelement.getText());
            System.out.println(countryelement.getText());
            Assert.assertEquals(nameelement.getText(),prop.getProperty("name"));
            System.out.println("Name Assert success");
            Assert.assertEquals(typeelement.getText(),prop.getProperty("type"));
            System.out.println("Type Assert success");
            Assert.assertEquals(countryelement.getText(),prop.getProperty("country"));
            System.out.println("Country Assert success");
        }catch(Exception e){
            e.printStackTrace();
        }


    }




}
