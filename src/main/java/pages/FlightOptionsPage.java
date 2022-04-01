package pages;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pojo.FlightDate;
import utils.ProjectLogger;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class FlightOptionsPage {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//div[@id = 'forwardFlight']//div[@class = 'flights-dates__title']")
    private SelenideElement departureFlightTitleContainer;

    @FindBy(how = How.XPATH, using = "//div[@id = 'backwardFlight']//div[@class = 'flights-dates__title']")
    private SelenideElement returnFlightTitleContainer;

    @FindBy(how = How.XPATH, using = "//div[@id = 'forwardFlight']//div[contains(@class, 'selected')]")
    private SelenideElement departureFlightDate;

    @FindBy(how = How.XPATH, using = "//div[@id = 'backwardFlight']//div[contains(@class, 'selected')]")
    private SelenideElement returnFlightDate;

    @FindBy(how = How.XPATH, using = "//button[@id = 'progressNextBtn']")
    private SelenideElement nextButton;

    private By departureFlightOptions = By.xpath("//div[@id = 'forwardFlight']//div[@class = 'tile__price']");

    private By departureFlightOptionButtons = By.xpath("//div[@id = 'forwardEconomModal']//span[text() = 'select']");

    private By returnFlightOptions = By.xpath("//div[@id = 'backwardFlight']//div[@class = 'tile__price']");

    private By returnFlightOptionButtons = By.xpath("//div[@id = 'backwardEconomModal']//span[text() = 'select']");


    // SCENARIOS =======================================================================================================


    public FlightOptionsPage checkDepartureFlightTitle(String departureCity, String arrivalCity) {
        departureFlightTitleContainer.shouldHave(Condition.text(departureCity), Duration.ofSeconds(8));
        departureFlightTitleContainer.shouldHave(Condition.text(arrivalCity), Duration.ofSeconds(8));
        ProjectLogger.getLogger(this.getClass()).info("Check element title: " + "--" + "DepartureFlightTitle" + "--" + " title contains: " + departureCity);
        ProjectLogger.getLogger(this.getClass()).info("Check element title: " + "--" + "DepartureFlightTitle" + "--" + " title contains: " + arrivalCity);
        return this;
    }


    public FlightOptionsPage checkReturnFlightTitle(String departureCity, String returnCity) {
        returnFlightTitleContainer.shouldHave(Condition.text(departureCity), Duration.ofSeconds(8));
        returnFlightTitleContainer.shouldHave(Condition.text(returnCity), Duration.ofSeconds(8));
        ProjectLogger.getLogger(this.getClass()).info("Check element title: " + "--" + "ReturnFlightTitle" + "--" + " title contains: " + departureCity);
        ProjectLogger.getLogger(this.getClass()).info("Check element title: " + "--" + "ReturnFlightTitle" + "--" + " title contains: " + returnCity);
        return this;
    }


    public FlightOptionsPage checkDepartureFlightDate(FlightDate date) {
        SelenideElement currentDate = $(departureFlightDate);
        currentDate.shouldBe(Condition.visible).shouldHave(Condition.text(date.getDay()));
        currentDate.shouldBe(Condition.visible).shouldHave(Condition.text(date.getMonth()));
        ProjectLogger.getLogger(this.getClass()).info("Check element: " + "--" + "DepartureFlightDate" + "--" + " day is: " + date.getDay());
        ProjectLogger.getLogger(this.getClass()).info("Check element: " + "--" + "DepartureFlightDate" + "--" + " month is: " + date.getMonth());
        return this;
    }


    public FlightOptionsPage checkReturnFlightDate(FlightDate date) {
        SelenideElement currentDate = $(returnFlightDate);
        currentDate.shouldBe(Condition.visible).shouldHave(Condition.text(date.getDay()));
        currentDate.shouldBe(Condition.visible).shouldHave(Condition.text(date.getMonth()));
        ProjectLogger.getLogger(this.getClass()).info("Check element: " + "--" + "ReturnFlightDate" + "--" + " day is: " + date.getDay());
        ProjectLogger.getLogger(this.getClass()).info("Check element: " + "--" + "ReturnFlightDate" + "--" + " month is: " + date.getMonth());
        return this;
    }


    public FlightOptionsPage setRandomDepartureFlightOption() {
        setRandomDepartureFlightOption(departureFlightOptions, departureFlightOptionButtons);
        return this;
    }


    public FlightOptionsPage setReturnRandomFlightOption() {
        setRandomDepartureFlightOption(returnFlightOptions, returnFlightOptionButtons);
        return this;
    }


    public WhoIsGoingToFlyPage clickNextButton() {
        nextButton.shouldBe(Condition.visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "nextButton" + "--");
        return page(WhoIsGoingToFlyPage.class);
    }


    private void setRandomDepartureFlightOption(By flightOption, By flightOptionButtons) {
        $(flightOption).shouldBe(Condition.visible).click();
        ElementsCollection flightOptions = $$(flightOptionButtons);
        flightOptions.shouldBe(CollectionCondition.sizeGreaterThan(0));

        int randomOption = ThreadLocalRandom.current().nextInt(0, flightOptions.size());

        flightOptions.get(randomOption).shouldBe(Condition.visible).click();
        flightOptions.get(randomOption).shouldBe(Condition.hidden);
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "flightOption" + "--");
    }
}
