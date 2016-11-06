package newtours.websites.features.stepDefns;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import newtours.website.pages.FindAFlightPage;
import newtours.website.pages.NewToursLoginPage;
import tests.environment.BrowserDriver;

/**
 * Created by sritej583 on 6/11/16.
 */
public class NewToursBookFlightStepDefinitions {

    private NewToursLoginPage newToursLoginPage;
    private FindAFlightPage findAFlightPage;

    @Given("^User launches the website \"([^\"]*)\"$")
    public void userLaunchesTheWebsite(String url) throws Throwable {
        BrowserDriver.getCurrentDriver().get(url);
        newToursLoginPage = new NewToursLoginPage(BrowserDriver.getCurrentDriver());
    }

    @When("^User enters the username as \"([^\"]*)\"$")
    public void userEntersTheUsernameAs(String username) throws Throwable {
        newToursLoginPage.setTxtUserName(username);
    }

    @And("^User enters the password as \"([^\"]*)\"$")
    public void userEntersThePasswordAs(String password) throws Throwable {
        newToursLoginPage.setPwdPassword(password);
    }

    @Then("^User successfully logged into webpage$")
    public void userSuccessfullyLoggedIntoWebpage() throws Throwable {
        findAFlightPage = newToursLoginPage.clickSignInBtn();
    }
}
