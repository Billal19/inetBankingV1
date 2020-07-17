package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties myProp;

    public ReadConfig(){
        File myPath = new File("Configurations/Config.properties");

        try {
            FileInputStream myInput = new FileInputStream(myPath);
            myProp= new Properties();
            myProp.load(myInput);

        }catch (Exception e){
            System.out.println("the exception is "+ e.getMessage());
        }
    }

    public String getApplicationUrl(){
        String url = myProp.getProperty("baseUrl");
        return url;
    }

    public String getUserName(){
        String username = myProp.getProperty("userName");
        return username;
    }

    public String getPassword(){
        String password = myProp.getProperty("password");
        return password;
    }

    public String getChromePAth(){
        String ChromePath = myProp.getProperty("chromePath");
        return ChromePath;
    }

    public String getFireFoxPath(){
        String FireFoxPath = myProp.getProperty("fireFoxPath");
        return FireFoxPath;
    }
}
