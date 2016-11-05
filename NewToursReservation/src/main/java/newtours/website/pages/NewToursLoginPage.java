package newtours.website.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sritej583 on 5/11/16.
 */
public class NewToursLoginPage {

    private WebDriver driver;

    @FindBy(name = "userName")
    private WebElement txtUserName;

    @FindBy(name = "password")
    private WebElement pwdPassword;

    @FindBy(name = "login")
    private WebElement signInBtn;

    public NewToursLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setTxtUserName(String userName) {
        txtUserName.clear();
        txtUserName.sendKeys(userName);
    }

    public void setPwdPassword(String password) {
        pwdPassword.clear();
        pwdPassword.sendKeys(password);
    }

    public FindAFlightPage clickSignInBtn() {
        signInBtn.click();
        return new FindAFlightPage(driver);
    }
}
