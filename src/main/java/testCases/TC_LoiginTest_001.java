package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.IOException;

public class TC_LoiginTest_001 extends BaseClass {

    @Test
    public void loginTest() throws IOException {
        myLogger.info("URL is opened");

        LoginPage myObject = new LoginPage(driver);
        myObject.setUserName(userName);
        myLogger.info("Entered username");

        myObject.setPassword(password);
        myLogger.info("entered password");

        myObject.clickSubmit();

//        System.out.println(driver.getTitle());

        if (driver.getTitle().equals("Guru99 Bank Manager HomePage")){
            Assert.assertTrue(true);
            myLogger.info("login test passed");
        }else {
            captureScreen(driver, "loginTest");
            Assert.assertTrue(false);
            myLogger.info("loging test failed");
        }

        driver.get(baseUrl);
    }
}
