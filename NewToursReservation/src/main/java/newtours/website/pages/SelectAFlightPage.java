package newtours.website.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sritej583 on 6/11/16.
 */
public class SelectAFlightPage {

    private WebDriver driver;
    private DepartSection departSection;
    private ReturnSection returnSection;

    public SelectAFlightPage(WebDriver driver) {
        this.driver = driver;

        if(!driver.getTitle().equals("Select a Flight: Mercury Tours")) {
            throw new WrongPageException("Expected Page is : " + "Select a Flight: Mercury Tours");
        }

        PageFactory.initElements(driver, this);
    }


    private class DepartSection {
    }

    private class ReturnSection {
    }
}
