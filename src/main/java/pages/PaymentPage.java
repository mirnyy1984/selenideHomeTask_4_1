package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import utils.ProjectLogger;

public class PaymentPage {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//input[@el = 'Card']")
    private SelenideElement cardNumberInput;

    @FindBy(how = How.XPATH, using = "//input[@el = 'MMYY']")
    private SelenideElement cardExpirationDateInput;

    @FindBy(how = How.XPATH, using = "//input[@id = 'cvv2']")
    private SelenideElement cardCvvCodeInput;


    // SCENARIOS =======================================================================================================


    public PaymentPage setCardNumber(String cardNumber) {
        Assert.assertEquals(cardNumber.length(), 16);

        int startIndex = 0;
        int lastIndex = 4;

        for (int i = 0; i < 4; i++) {
            String text = cardNumber.substring(startIndex, lastIndex);
            cardNumberInput.shouldBe(Condition.visible).sendKeys(text);
            startIndex = lastIndex;
            lastIndex += 4;
        }

        ProjectLogger.getLogger(this.getClass()).info("Type to element: " + "--" + "cardNumberInput" + "--" + "text: " + cardNumber);
        return this;
    }


    public PaymentPage setCardExpirationDate(String date) {
        String[] dates = date.split("/");
        Assert.assertEquals(dates.length, 2);

        for (String dateData : dates) {
            cardExpirationDateInput.shouldBe(Condition.visible).sendKeys(dateData);
        }

        ProjectLogger.getLogger(this.getClass()).info("Type to element: " + "--" + "cardExpirationDateInput" + "--" + "text: " + date);
        return this;
    }


    public PaymentPage setCardCvvCode(String cvvCode) {
        Assert.assertEquals(cvvCode.length(), 3);
        cardCvvCodeInput.shouldBe(Condition.visible).sendKeys(cvvCode);
        ProjectLogger.getLogger(this.getClass()).info("Type to element: " + "--" + "cardCvvCodeInput" + "--" + "text: " + cvvCode);
        return this;
    }
}
