package testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.XLUtils;

import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass {



    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(user);
        myLogger.info("username is provided");
        lp.setPassword(pwd);
        myLogger.info("password is provided");
        lp.clickSubmit();
        Thread.sleep(3000);

        if (isAlertPresent()==true){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            myLogger.warn("Login failed");
        }
        else {
            Assert.assertTrue(true);
            myLogger.info("test is passed");
            lp.ClickLogOut();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();//close logout alert
            driver.switchTo().defaultContent();
        }

    }
    public boolean isAlertPresent(){ // user defined method used to check if the alert is present or not
        try {
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }

    }

    @DataProvider(name="LoginData")
    Object[][] getData() throws IOException {

        String path="/Users/billalyahiaoui/IdeaProjects/inetBankingV1_FrameWork/src/main/java/testData/DataDrivenTest.xlsx";
        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path,"Sheet1",1);
        String loginData[][]= new String[rownum][colcount];
        for (int i=1;i<rownum;i++){
            for (int j=0;j<colcount;j++){

                loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }

        return loginData;


    }



    }

