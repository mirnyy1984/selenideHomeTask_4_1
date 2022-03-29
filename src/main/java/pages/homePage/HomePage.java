package pages.homePage;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.ProjectLogger;


public class HomePage {

    public Header header;
    public AirWaySelector airWaySelector;

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//div[@id = 'open-appeal-modal']")
    private SelenideElement welcomeModalWindow;

    @FindBy(how = How.XPATH, using = "//div[@id = 'open-appeal-modal']//button[contains(@class, 'close')]")
    private SelenideElement welcomeModalWindowCloseButton;

    // SCENARIOS =======================================================================================================

    public HomePage closeWelcomeModalIfPresent() {
        try {
            welcomeModalWindowCloseButton.shouldBe(visible).click();
            welcomeModalWindow.shouldBe(hidden);
            ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--welcomeModalWindowCloseButton--");

        } catch (Throwable e) {
            ProjectLogger.getLogger(this.getClass()).info("element: " + "--welcomeModalWindowCloseButton--" + " not present");
        }
        return this;
    }

    public HomePage acceptCookies() {
        $(By.xpath("//div[@id = 'cookieModal']//button")).shouldBe(visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--acceptButton--");
        return this;
    }
}
