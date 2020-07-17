package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver Ldriver;
    public LoginPage(WebDriver Rdriver){
        Ldriver=Rdriver;
        PageFactory.initElements(Rdriver,this);

    }

    // in here we are identifying the elements :
    @FindBy(name = "uid")
    WebElement txtUserName;

    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;

    @FindBy(name = "btnLogin")
    WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Log out')]")
    @CacheLookup
    WebElement linkLogOut;

    // we will be creating the action methods for these elements:
    public void setUserName(String uname){
        txtUserName.sendKeys(uname);
    }

    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }

    public void clickSubmit(){
        loginButton.click();
    }

    public void ClickLogOut(){
        linkLogOut.click();

    }
}
