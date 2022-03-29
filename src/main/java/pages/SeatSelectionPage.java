package pages;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.ProjectLogger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class SeatSelectionPage {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//div[@class = 'seats__row-item']//span")
    private List<SelenideElement> seats;

    @FindBy(how = How.XPATH, using = "//button[@id = 'boardingContinueBtn']")
    private SelenideElement continueButton;

    @FindBy(how = How.XPATH, using = "(//button[@data-tab = 'backward'])[1]")
    private SelenideElement backwardFlyTabButton;

    private By forwardFlightSeats = By.xpath("//div[@data-flight-direction = 'forward']//div[@class = 'seats__row-item']//span");

    private By forwardFlightSelectedSeatCheckbox = By.xpath("(//span[contains(@class, 'checkbox-title')])[1]");

    private By backwardFlightSeats = By.xpath("//div[@data-flight-direction = 'backward']//div[@class = 'seats__row-item']//span");

    private By backwardFlightSelectedSeatCheckbox = By.xpath("(//span[contains(@class, 'checkbox-title')])[2]");

    // SCENARIOS =======================================================================================================

    public SeatSelectionPage selectForwardFlightSeat() {
        selectRandomSeat(forwardFlightSeats, forwardFlightSelectedSeatCheckbox);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "selectForwardFlightSeat" + "--");
        return this;
    }

    public SeatSelectionPage selectBackwardFlightSeat() {
        selectRandomSeat(backwardFlightSeats, backwardFlightSelectedSeatCheckbox);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "selectBackwardFlightSeat" + "--");
        return this;
    }

    public WhoIsGoingToFlyPage clickContinueButton() {
        continueButton.shouldBe(Condition.visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element" + "--" + "continueButton" + "--");
        return page(WhoIsGoingToFlyPage.class);
    }

    public SeatSelectionPage openBackWardFlyTab() {
        backwardFlyTabButton.click();
        ProjectLogger.getLogger(this.getClass()).info("Click element" + "--" + "backwardFlyTabButton" + "--");
        return this;
    }

    private SeatSelectionPage selectRandomSeat(By flightSeats, By selectedSeatCheckbox) {
        ElementsCollection elements = $$(flightSeats);
        elements.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(8));

        List<SelenideElement> availableSeats = new ArrayList<>(elements)
                .stream()
                .filter(seat -> !Objects.requireNonNull(seat.getAttribute("class")).contains("unavailable"))
                .toList();

        availableSeats
                .get(ThreadLocalRandom.current().nextInt(0, availableSeats.size() - 1))
                .scrollTo()
                .shouldBe(Condition.visible)
                .click();

        $(selectedSeatCheckbox)
                .shouldBe(Condition.visible, Duration.ofSeconds(8))
                .click();

        return this;
    }
}
