package testCases;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BaseClass {

    ReadConfig myObj = new ReadConfig(); // we created an object of the ReadConfig class , we can access the methods to read the properties

    public String baseUrl =myObj.getApplicationUrl();
    public String userName =myObj.getUserName();
    public String password = myObj.getPassword();
    public static WebDriver driver;
    public static Logger myLogger;

    // Note: every test case needs a SetUp method ==>testCaseSteps==>TearDown method ,
    // since the SetUp method  and TearDown method  are required for every test case , so there is no need to repeat them in every test case
    // we will have to create a base where we can have these two method , and this BaseClass will be extended by the test cases , so they can use the two required methods
    @Parameters("browser") // we added this parameter annotation with the value of "browser" so we can execute our test cases on a desired browser
    // this annotation should be added in our testNG xml file , so can execute our test cases on a desired browser by changing the value and the name in <parameter> tag
    @BeforeClass
    public void setup(String br){

        // the Logger class is used here to add logs to our test cases
        myLogger = Logger.getLogger("ebanking");
        //After i created the Log4j.properties , it was empty at first , everytime i run my code , i be getting warning as an output
        // i was getting a warning that no appender could be found , also another warning that i need to set the Log4j.properties properly
        // i am not sure what the problem is here , i copied a template from google and pasted it in Log4j.properties, then my program executed without warnings
        // Also before pasting the template from google , i used this class BasicConfigurator.configure(); then my program got executed without warnings
       // BasicConfigurator.configure();
        PropertyConfigurator.configure("Log4j.properties");
        //myLogger.debug("Log4j appender configuration is successful !!");

        // we put the condition of the browser , to see what browser we wanted to use to execute our test cases
        if (br.equals("chrome")){

            System.setProperty("webdriver.chrome.driver",myObj.getChromePAth());
            driver=new ChromeDriver();

        }else if (br.equals("firefox")){

            System.setProperty("webdriver.gecko.driver",myObj.getFireFoxPath());
            driver=new FirefoxDriver();

        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot myObject = (TakesScreenshot) driver;
        File source = myObject.getScreenshotAs(OutputType.FILE);
        File target = new File("/Users/billalyahiaoui/IdeaProjects/inetBankingV1_FrameWork/ScreenShots"+tname+".png");
        FileUtils.copyFile(source,target);
        System.out.println("ScreenShot taken");
    }




}
