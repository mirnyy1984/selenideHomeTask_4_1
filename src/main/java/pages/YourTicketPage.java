package pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.ProjectLogger;


public class YourTicketPage {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//input[@id = 'contactPersonEmail1']")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@id = 'contactPersonEmail2']")
    private SelenideElement repeatEmailInput;

    @FindBy(how = How.XPATH, using = "//input[@id = 'userPhoneFieldInput']")
    private SelenideElement phoneNumberInput;

    @FindBy(how = How.XPATH, using = "(//input[@id = 'TotalTermsCheckbox']/following-sibling::span)[1]")
    private SelenideElement iAgreeCheckbox;

    @FindBy(how = How.XPATH, using = "//button[@id = 'totalFixedPayBtn']//span")
    private SelenideElement payButton;


    // SCENARIOS =======================================================================================================


    public YourTicketPage setEmail(String email) {
        emailInput.shouldBe(Condition.visible).sendKeys(email);
        ProjectLogger.getLogger(this.getClass()).info("Type to element: " + "--" + "emailInput" + "--" + " text is: " + email);
        return this;
    }


    public YourTicketPage repeatEmail(String email) {
        repeatEmailInput.shouldBe(Condition.visible).sendKeys(email);
        ProjectLogger.getLogger(this.getClass()).info("Type to element: " + "--" + "repeatEmailInput" + "--" + " text is: " + email);
        return this;
    }


    public YourTicketPage setPhoneNumber(String phoneNumber) {
        phoneNumberInput.shouldBe(Condition.visible).sendKeys(phoneNumber);
        ProjectLogger.getLogger(this.getClass()).info("Type to element: " + "--" + "phoneNumberInput" + "--" + " text is: " + phoneNumber);
        return this;
    }


    public YourTicketPage clickIAgreeCheckbox() {
        iAgreeCheckbox
                .scrollTo()
                .shouldBe(Condition.visible)
                .click();

        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "iAgreeCheckbox" + "--");
        return this;
    }


    public PaymentPage clickPayButton() {
        executeJavaScript("arguments[0].click()", payButton);
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "payButton" + "--");
        return page(PaymentPage.class);
    }
}
