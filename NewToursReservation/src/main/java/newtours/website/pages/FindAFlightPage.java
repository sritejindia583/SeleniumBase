package newtours.website.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by sritej583 on 5/11/16.
 */
public class FindAFlightPage {
    private WebDriver driver;
    private FlightDetails flightDetails;
    private Preferences preferences;

    public FindAFlightPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
            throw new WrongPageException("Expected Page is : " + "Find a Flight: Mercury Tours:");
        }
        PageFactory.initElements(driver, this);
    }

    public SelectAFlightPage populateFindAFlighPage(String tripType,
                                                    String passengersCount,
                                                    String departingFrom,
                                                    String departingDate,
                                                    String arrivingIn,
                                                    String returningDate,
                                                    String serviceClass,
                                                    String airline) {

        populateFlightDetails(tripType, passengersCount, departingFrom, departingDate, arrivingIn, returningDate);
        populatePreferences(serviceClass, airline);

        return new SelectAFlightPage(driver);
    }

    public void populateFlightDetails(String tripType,
                                      String passengersCount,
                                      String departingFrom,
                                      String departingDate,
                                      String arrivingIn,
                                      String returningDate) {

        flightDetails.populateRadioButton(tripType, flightDetails.tripTypeGroup);
        flightDetails.selectDropDown(flightDetails.passengers, passengersCount);
        flightDetails.selectDropDown(flightDetails.departingFrom, departingFrom);
        flightDetails.selectDropDown(flightDetails.departingOn.fromMonth, departingDate.split("/")[0]);
        flightDetails.selectDropDown(flightDetails.departingOn.fromDay, departingDate.split("/")[1]);
        flightDetails.selectDropDown(flightDetails.arrivingIn, arrivingIn);
        flightDetails.selectDropDown(flightDetails.returning.toMonth, returningDate.split("/")[0]);
        flightDetails.selectDropDown(flightDetails.returning.toDay, returningDate.split("/")[1]);

    }


    public void populatePreferences(String serviceClass,
                                    String airline) {
        flightDetails.populateRadioButton(serviceClass, preferences.serviceClassGroup);
        flightDetails.selectDropDown(preferences.airLine, airline);
    }


    private class FlightDetails {

        @FindBys({
                @FindBy(name = "tripType")
        })
        public List < WebElement > tripTypeGroup;

        @FindBy(name = "passCount")
        public WebElement passengers;

        @FindBy(name = "fromPort")
        public WebElement departingFrom;

        public On departingOn;

        @FindBy(name = "toPort")
        public WebElement arrivingIn;

        public Returning returning;

        public void populateRadioButton(String tripType,
                                        List < WebElement > radioButtonGroup) {

            for (WebElement element: radioButtonGroup) {
                String value = element.getAttribute("value");

                System.out.println("Radio button value present is: " + value);

                if (value.equals(tripType)) {
                    element.click();
                    break;
                }
            }
        }

        public void selectDropDown(WebElement element,
                                   String option) {
            Select selectMenu = new Select(element);
            List < WebElement > selectOptions = selectMenu.getOptions();

            for (WebElement tempElement: selectOptions) {
                if (tempElement.getText().equals(option)) {
                    tempElement.click();
                    break;
                }
            }

        }
    }

    private class Preferences {
        @FindBys({
                @FindBy(name = "servClass")
        })
        public List < WebElement > serviceClassGroup;

        @FindBy(name = "airline")
        public WebElement airLine;
    }

    private class On {
        @FindBy(name = "fromMonth")
        public WebElement fromMonth;

        @FindBy(name = "fromDay")
        public WebElement fromDay;
    }

    private class Returning {
        @FindBy(name = "toMonth")
        public WebElement toMonth;

        @FindBy(name = "toDay")
        public WebElement toDay;
    }
}