import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class Assessment extends BaseTest{

    @Test
    public void logintest(){
        JSONParser parser = new JSONParser();
        PageObjects p=new PageObjects(driver);
        try{
            Object object = parser.parse(new FileReader("C:\\Users\\siddharthagupta\\IdeaProjects\\maven-demo\\src\\test\\resources\\TestData.json"));
            JSONObject jsonObject = (JSONObject) object;
            String user = (String) jsonObject.get("username");
            String pass = (String) jsonObject.get("password");
            JSONArray lgn = (JSONArray) jsonObject.get("userlogins");
            System.out.println("USERNAME " +user );
            System.out.println("PASSWORD "+ pass);
            System.out.println("USERLOGINS: ");
            for(int i=0;i<lgn.size();i++){
                JSONObject users=(JSONObject) lgn.get(i);
                String uname=(String)users.get("username");
                System.out.println(uname);
                String pwd=(String)users.get("password");
                System.out.println(pwd);
                p.Login(uname,pwd);
                System.out.println("Login successfull");
                p.Logout(uname);
                System.out.println("Logout successfull");
            }

        }catch (Exception e){
            e.printStackTrace();
            exitOnFailure(e);
        }


    }


}
