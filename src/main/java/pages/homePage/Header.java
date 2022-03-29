package pages.homePage;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import utils.ProjectLogger;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Condition.visible;

public class Header {

    // WEB-ELEMENTS ====================================================================================================

    @FindBy(how = How.XPATH, using = "//button[@id = 'showLangsDesktop']")
    private SelenideElement mainLanguageButton;

    @FindBy(how = How.XPATH, using = "//ul[@id = 'headerLangListDesktop']")
    private SelenideElement languagePopUp;

    @FindBy(how = How.XPATH, using = "//ul[@id = 'headerLangListDesktop']//button")
    private List<SelenideElement> languageButtons;

    @FindBy(how = How.XPATH, using = "//button[@id = 'showCurrencyDesktop']")
    private SelenideElement mainCurrencyButton;

    @FindBy(how = How.XPATH, using = "//ul[@id = 'headerCurrencyListDesktop']//button")
    private List<SelenideElement> currencyButtons;

    @FindBy(how = How.XPATH, using = "//ul[@id = 'headerCurrencyListDesktop']")
    private SelenideElement currencyPopUp;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class, 'header__nav')]//li")
    private ElementsCollection headerLinks;

    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'header__logo-link')]")
    private SelenideElement logo;

    @FindBy(how = How.XPATH, using = "//button[@id = 'headerLogin']")
    private SelenideElement loginButton;

    // SCENARIOS =======================================================================================================


    public Header clickMainLanguageButton() {
        mainLanguageButton.shouldBe(visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--mainLanguageButton--");
        return this;
    }

    public Header selectLanguage(String language) {
        languagePopUp.shouldBe(visible);
        ProjectLogger.getLogger(this.getClass()).info("element: " + "--languagePopUp--" + " is visible");

        languageButtons.stream().filter(button -> button.getText().equalsIgnoreCase(language)).findFirst().get().click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + language + "--" + " Button");
        return this;
    }

    public Header checkMainLanguageButtonText(String text) {
        mainLanguageButton.shouldBe(text(text));
        ProjectLogger.getLogger(this.getClass()).info("Check element text: " + "--mainLanguageButton--" + " text is " + "\"" + text + "\"");
        return this;
    }

    public Header checkLanguagePopUpButtonsText(String firstButtonLanguageText) {
        languagePopUp.shouldBe(visible);
        boolean isButtonsPresent = languageButtons.stream()
                .filter(button -> button.getText().equals(firstButtonLanguageText))
                .toList().size() == 1;

        Assert.assertTrue(isButtonsPresent);
        ProjectLogger.getLogger(this.getClass()).info("Check element text: " + "--" + "languagePopUp" + "--");
        ProjectLogger.getLogger(this.getClass()).info("element: " + "--" + "Button" + "--" + " text is: " + firstButtonLanguageText);
        return this;
    }

    public Header checkHeaderLinksCount() {
        Assert.assertEquals(headerLinks.size(), 7);
        ProjectLogger.getLogger(this.getClass()).info("Check element: " + "--headerLinks--" + " count is " + headerLinks.size());
        return this;
    }


    public Header clickHeaderLink(String linkText) {
        headerLinks.find(Condition.text(linkText)).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + linkText + "--");
        return this;
    }

    public Header clickHeaderDropdownLink(String linkText) {
        $$(By.xpath("//a[contains(@class, 'header-dropdown__link')]/span")).find(Condition.text(linkText)).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + linkText + "--");
        return this;
    }

    public Header hoverHeaderLink(String linkText) {
        headerLinks.find(Condition.text(linkText)).hover();
        ProjectLogger.getLogger(this.getClass()).info("Hover element: " + "--" + linkText + "--");
        return this;
    }

    public Header checkUrl(String pageRout) {
        webdriver().shouldHave(urlContaining(pageRout));
        ProjectLogger.getLogger(this.getClass()).info("Check page url: " + pageRout);
        return this;
    }

    public void checkLogoIsVisible() {
        logo.shouldBe(visible);
        ProjectLogger.getLogger(this.getClass()).info("Check element: " + "--" + "logo" + "--" + "logo is visible");
    }


    public Header clickMainCurrencyButton() {
        mainCurrencyButton.shouldBe(visible).click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + "mainCurrencyButton" + "--");
        return this;
    }

    public Header selectCurrency(String currency) {
        currencyPopUp.shouldBe(visible);
        currencyButtons.stream().filter(button -> button.getText().equalsIgnoreCase(currency)).findFirst().get().click();
        ProjectLogger.getLogger(this.getClass()).info("Click element: " + "--" + currency + " Button" + "--");
        return this;
    }

    public Header checkMainCurrencyButtonText(String text) {
        mainCurrencyButton.shouldHave(text(text));
        ProjectLogger.getLogger(this.getClass()).info("Check element text: " + "--" + "mainCurrencyButton" + "--" + " text is " + text);
        return this;
    }


    public Header checkCurrencyPopUpButtonsText(String firstButtonCurrencyText, String secondButtonCurrencyText) {
        currencyPopUp.shouldBe(visible);
        boolean isButtonsPresent = currencyButtons.stream()
                .filter(button -> button.getText().equals(firstButtonCurrencyText) || button.getText().equals(secondButtonCurrencyText))
                .toList().size() == 2;

        Assert.assertTrue(isButtonsPresent);

        ProjectLogger.getLogger(this.getClass()).info("Check element text: " + "--" + "languagePopUp" + "--");
        ProjectLogger.getLogger(this.getClass()).info("element: " + "--" + "Button" + "--" + " text is: " + firstButtonCurrencyText);
        ProjectLogger.getLogger(this.getClass()).info("element: " + "--" + "Button" + "--" + " text is: " + secondButtonCurrencyText);
        return this;
    }
}
