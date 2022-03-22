import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginTest extends BaseTest{

    @Test(dataProvider = "dp")
    public void logintest(String uname,String pwd){
        try{
            LoginPageObject lp=new LoginPageObject(driver);
            lp.Login(uname,pwd);
            System.out.println("Login successfull");
            if(uname.equalsIgnoreCase("admin")){
                Assert.assertEquals(lp.admintext.getText(),"IceHrm Employee");
            }else if(uname.equalsIgnoreCase("manager")){
                Assert.assertEquals(lp.managertext.getText(),"Lala Lamees");
            }else if(uname.equalsIgnoreCase("user1")){
                Assert.assertEquals(lp.user1text.getText(),"Sofia O'Sullivan");
            }
            else if(uname.equalsIgnoreCase("user2")){
                Assert.assertEquals(lp.user2text.getText(),"Taylor Holmes");
            }
            lp.Logout(uname);
            System.out.println("Logout successfull");
        }catch(Exception e){
            e.printStackTrace();
            exitOnFailure(e);
        }


    }


    @DataProvider(name="dp")
    public Iterator<Object[]> ddlogintest() {
        JSONParser parser = new JSONParser();

        ArrayList<Object[]> mydata = null;
        try {
            Object object = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.json"));
            JSONObject jsonObject = (JSONObject) object;
            String user = (String) jsonObject.get("username");
            String pass = (String) jsonObject.get("password");
            JSONArray lgn = (JSONArray) jsonObject.get("userlogins");
            System.out.println("USERNAME " + user);
            System.out.println("PASSWORD " + pass);
            System.out.println("USERLOGINS: ");
            mydata = new ArrayList<Object[]>();
            String arr[] = new String[lgn.size()];
            for (int i = 0; i < lgn.size(); i++) {
                JSONObject users = (JSONObject) lgn.get(i);
                String uname = (String) users.get("username");
                System.out.println(uname);
                String pwd = (String) users.get("password");
                System.out.println(pwd);
                Object ob[] = {uname, pwd};
                mydata.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            exitOnFailure(e);
        }

        return mydata.iterator();
    }


}
