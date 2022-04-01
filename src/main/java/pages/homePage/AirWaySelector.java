package pages.homePage;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.FlightOptionsPage;
import pojo.FlightDate;
import utils.ProjectLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class AirWaySelector {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//div[@id = 'deprtureCity']")
    private SelenideElement departureCity;

    @FindBy(how = How.XPATH, using = "//div[@id = 'arrivalCity']")
    private SelenideElement arrivalCity;

    @FindBy(how = How.XPATH, using = "//div[@id = 'forwardDateItem']")
    private SelenideElement forwardDatePicker;

    @FindBy(how = How.XPATH, using = "//div[@id = 'backwardDateItem']")
    private SelenideElement backwardCityDatePicker;

    private By citiesListPath = By.xpath("//ul[@id = 'citiesList']/li");

    private By nextMonthButtonPath = By.xpath("//div[@id = 'datesModalContent']//span[@class = 'next']");

    private By prevMonthButtonPath = By.xpath("//div[@id = 'datesModalContent']//span[@class = 'prev']");

    private By currentMonthPath = By.xpath("(//div[@id = 'datesModalContent']//div[@class = 'month-element'])[1]");

    private By availableDaysPath = By.xpath("//div[contains(@class, 'tooltip-trigger')]");


    // SCENARIOS =======================================================================================================


    public AirWaySelector clickDepartureCity() {
        departureCity.shouldBe(Condition.visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "departureCity" + "--");
        return this;
    }


    public AirWaySelector clickArrivalCity() {
        arrivalCity.shouldBe(Condition.visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "arrivalCity" + "--");
        return this;
    }


    public AirWaySelector checkDepartureCityTitle(String title) {
        departureCity.$(By.xpath(".//input[@id = 'deprtureCityName']")).shouldHave(Condition.value(title));
        ProjectLogger.getLogger(this.getClass()).info("Check element title: " + "--" + "departureCity" + "--" + " title is: " + title);
        return this;
    }


    public AirWaySelector checkArrivalCityTitle(String title) {
        arrivalCity.$(By.xpath(".//input[@id = 'arrivalCityName']")).shouldHave(Condition.value(title));
        ProjectLogger.getLogger(this.getClass()).info("Check element title: " + "--" + "arrivalCity" + "--" + " title is: " + title);
        return this;
    }


    public AirWaySelector selectCity(String cityName) {
        new ArrayList<>($$(citiesListPath))
                .stream()
                .filter(city -> Objects.requireNonNull(city.getAttribute("data-city-name")).contains(cityName))
                .findFirst()
                .get()
                .scrollIntoView(true)
                .click();

        ProjectLogger.getLogger(this.getClass()).info("Select element: " + "--" + cityName + "--");
        return this;
    }


    public AirWaySelector clickForwardDatePicker() {
        forwardDatePicker.shouldBe(Condition.visible).scrollTo().click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "forwardDatePicker" + "--");
        return this;
    }


    public AirWaySelector clickBackwardCityDatePicker() {
        backwardCityDatePicker.shouldBe(Condition.visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "backwardCityDatePicker" + "--");
        return this;
    }


    public FlightDate selectAvailableDay() {
        ElementsCollection days = $$(availableDaysPath);
        SelenideElement nextMonthButton = $(nextMonthButtonPath);

        while (!isAvailableDayPresent(days)) {
            nextMonthButton.click();
            days = $$(availableDaysPath);
        }

        List<SelenideElement> clearDays = new ArrayList<>(days)
                .stream()
                .filter(day -> Objects.requireNonNull(day.getAttribute("class")).contains("day toMonth valid"))
                .toList();

        String month = $(currentMonthPath).text().trim();
        int randomDay = ThreadLocalRandom.current().nextInt(0, clearDays.size());
        SelenideElement day = clearDays.get(randomDay);
        day.click();

        ProjectLogger.getLogger(this.getClass()).info("Select available day element: " + "--" + "day: " + day.getOwnText() + "; " + "month: " + month + "--");
        return new FlightDate(day.getOwnText(), month);
    }


    public FlightOptionsPage clickShowFlightsButton() {
        $(By.xpath("//button[@id = 'searchBtn']")).shouldBe(Condition.visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "ShowFlightsButton" + "--");
        return page(FlightOptionsPage.class);
    }


    private boolean isAvailableDayPresent(ElementsCollection days) {
        return days.size() > 0;
    }
}
