package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

import java.io.IOException;
import java.util.Random;

public class TC_AddCustomerTest_003 extends BaseClass {


    @Test
    public void AddNewCustomer() throws InterruptedException, IOException {

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(userName);
        myLogger.info("User Name Provided");
        lp.setPassword(password);
        myLogger.info("Password Provided");
        lp.clickSubmit();

        Thread.sleep(3000);

        AddCustomerPage addcust = new AddCustomerPage(driver);
        addcust.clickAddNewCustomer();
        myLogger.info("Providing customer details .....");
        addcust.customerName("Billal");
        addcust.customerGender("male");
        addcust.customerDOB("01","02","2020");
        Thread.sleep(3000);
        addcust.customerAddress("new york");
        addcust.customerCity("brooklyn");
        addcust.customerState("NY");
        addcust.customerPinno(101010);
        addcust.customerPhoneNumber("625736427");

        String email =randomString()+"@gmail.com";
        addcust.customerEmailid(email);

        addcust.customerPassword("abcdef");
        addcust.customerSubmit();
        Thread.sleep(3000);

        myLogger.info("Validation started ......");

        boolean result =driver.getPageSource().contains("Customer Registered Successfully!!!");

        if (result==true){
            Assert.assertTrue(true);
            myLogger.info("test case is passed ");
        }
        else{
            captureScreen(driver,"addNewCustomer");
            Assert.assertTrue(false);
            myLogger.info("test case is failed");
        }

    }

    public String randomString(){
        String generatedString= RandomStringUtils.randomAlphabetic(8);
        return (generatedString);
    }

    public static String randomeNum(){
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }


}
