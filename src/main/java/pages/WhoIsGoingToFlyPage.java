package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.ProjectLogger;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class WhoIsGoingToFlyPage {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'first-name')]//input")
    private SelenideElement firstNameInput;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'last-name')]//input")
    private SelenideElement lastNameInput;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'passenger__date-selects')]//div[contains(@class, 'day')])[1]//select")
    private SelenideElement dayOfBirth;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'passenger__date-selects')]//div[contains(@class, 'month')])[1]//select")
    private SelenideElement monthOfBirth;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'passenger__date-selects')]//div[contains(@class, 'year')])[1]//select")
    private SelenideElement yearOfBirth;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gender')]//select")
    private SelenideElement genderInput;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'citizenship')]//select")
    private SelenideElement citizenInput;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'passport-number')]//input")
    private SelenideElement passportNumberInput;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'passenger__date-selects')]//div[contains(@class, 'day')])[2]//select")
    private SelenideElement passportExpirationDay;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'passenger__date-selects')]//div[contains(@class, 'month')])[2]//select")
    private SelenideElement passportExpirationMonth;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'passenger__date-selects')]//div[contains(@class, 'year')])[2]//select")
    private SelenideElement passportExpirationYear;

    @FindBy(how = How.XPATH, using = "//button[@id = 'progressNextBtn']")
    private SelenideElement nextButton;

    @FindBy(how = How.XPATH, using = "//div[@id = 'dontStarveModal']")
    private SelenideElement starveModalWindow;

    @FindBy(how = How.XPATH, using = "(//img[@alt = 'Seat'])[1]")
    private SelenideElement selectSeat;


    // SCENARIOS =======================================================================================================

    public WhoIsGoingToFlyPage typeFirstName(String name) {
        firstNameInput.sendKeys(name);
        ProjectLogger.getLogger(this.getClass()).info("Type to element" + "--" + "firstNameInput" + "--" + "text is: " + name);
        return this;
    }

    public WhoIsGoingToFlyPage typeLastName(String name) {
        lastNameInput.sendKeys(name);
        ProjectLogger.getLogger(this.getClass()).info("Type to element" + "--" + "lastNameInput" + "--" + "text is: " + name);
        return this;
    }

    public WhoIsGoingToFlyPage setDayOfBirth(String day) {
        dayOfBirth.selectOption(day);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "dayOfBirth" + "--" + "text is: " + day);
        return this;
    }

    public WhoIsGoingToFlyPage setMonthOfBirth(String month) {
        monthOfBirth.selectOption(month);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "monthOfBirth" + "--" + "text is: " + month);
        return this;
    }

    public WhoIsGoingToFlyPage setYearOfBirth(String year) {
        yearOfBirth.selectOption(year);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "yearOfBirth" + "--" + "text is: " + year);
        return this;
    }

    public WhoIsGoingToFlyPage setGender(String gender) {
        genderInput.selectOption(gender);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "genderInput" + "--" + "text is: " + gender);
        return this;
    }

    public WhoIsGoingToFlyPage setCitizen(String citizen) {
        citizenInput.selectOption(citizen);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "citizenInput" + "--" + "text is: " + citizen);
        return this;
    }

    public WhoIsGoingToFlyPage setPassportNumber(String number) {
        passportNumberInput.sendKeys(number);
        ProjectLogger.getLogger(this.getClass()).info("Type to element" + "--" + "passportNumberInput" + "--" + "text is: " + number);
        return this;
    }

    public WhoIsGoingToFlyPage setPassportExpirationDay(String day) {
        passportExpirationDay.selectOption(day);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "passportExpirationDay" + "--" + "text is: " + day);
        return this;
    }

    public WhoIsGoingToFlyPage setPassportExpirationMonth(String month) {
        passportExpirationMonth.selectOption(month);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "passportExpirationMonth" + "--" + "text is: " + month);
        return this;
    }

    public WhoIsGoingToFlyPage setPassportExpirationYear(String year) {
        passportExpirationYear.selectOption(year);
        ProjectLogger.getLogger(this.getClass()).info("Select element" + "--" + "passportExpirationYear" + "--" + "text is: " + year);
        return this;
    }

    public WhoIsGoingToFlyPage clickNextButton() {
        nextButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element" + "--" + "nextButton" + "--");
        return page(WhoIsGoingToFlyPage.class);
    }

    public SeatSelectionPage selectSeat() {
        selectSeat.click();
        ProjectLogger.getLogger(this.getClass()).info("Click element" + "--" + "selectSeat" + "--");
        return page(SeatSelectionPage.class);
    }

    public YourTicketPage closeStarveModalWindow() {
        try {
            $x("//button[@id = 'dontStarveModalCloseBtn']")
                    .shouldBe(Condition.visible)
                    .click();
            ProjectLogger.getLogger(this.getClass()).info( "--" + "closeStarveModalWindow" + "--");
        } catch (Throwable e) {
            ProjectLogger.getLogger(this.getClass()).info("Element" + "--" + "closeStarveModalWindow" + "--" + "not present");
        }

        try {
            clickNextButton();
            ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "nextButton" + "--");
        } catch (Throwable e1) {
            ProjectLogger.getLogger(this.getClass()).info("Element" + "--" + "nextButton" + "--" + "not present");
        }
        return page(YourTicketPage.class);
    }
}
