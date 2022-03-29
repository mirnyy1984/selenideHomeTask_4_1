package pages;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class BasePage {

    public BasePage checkUrl() {
        //webdriver().shouldHave(urlContaining(pageRout));
        return this;
    }
}
