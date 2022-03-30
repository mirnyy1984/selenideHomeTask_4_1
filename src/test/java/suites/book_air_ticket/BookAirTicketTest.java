package suites.book_air_ticket;

import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import pojo.FlightDate;
import suites.TestBase;


@Listeners({ScreenShooter.class})
public class BookAirTicketTest extends TestBase {

    @Test
    public void test_1_checkWebpageLanguage() {
        homePage
                .closeWelcomeModalIfPresent();

        homePage.header
                .checkMainLanguageButtonText("УКР")
                .clickMainLanguageButton()
                .checkLanguagePopUpButtonsText("ENG")
                .selectLanguage("ENG")
                .checkMainLanguageButtonText("ENG")
                .clickMainLanguageButton()
                .checkLanguagePopUpButtonsText("УКР");
    }


    @Test
    public void test_2_checkHeaderLinksCount() {
        homePage
                .closeWelcomeModalIfPresent();

        homePage.header
                .checkHeaderLinksCount();
    }

    @Test
    public void test_3_checkHeaderLinks() {
        homePage
                .closeWelcomeModalIfPresent();

        //check links
        homePage.header
                .clickHeaderLink("Мої бронювання")
                .checkUrl(Routs.MY_TICKETS.getRout())
                .clickHeaderLink("Маршрути")
                .checkUrl(Routs.ROUTES_MAP.getRout())
                .clickHeaderLink("Всі рейси")
                .checkUrl(Routs.ALL_FLIGHTS.getRout())
                .clickHeaderLink("Кар'єра")
                .checkUrl(Routs.CAREER.getRout())
                .clickHeaderLink("Контакти")
                .checkUrl(Routs.CONTACTS.getRout());


        //check dropdown "Пасажирам"
        homePage.header
                .hoverHeaderLink("Пасажирам")
                .clickHeaderDropdownLink("Важливо та корисно")
                .checkUrl(Routs.FAQ.getRout())
                .hoverHeaderLink("Пасажирам")
                .clickHeaderDropdownLink("Новини")
                .checkUrl(Routs.NEWS.getRout())
                .hoverHeaderLink("Пасажирам")
                .clickHeaderDropdownLink("Наш флот")
                .checkUrl(Routs.OUR_FLEET.getRout())
                .hoverHeaderLink("Пасажирам")
                .clickHeaderDropdownLink("Наші напрямки")
                .checkUrl(Routs.OUR_DIRECTIONS.getRout());

        //check dropdown "Проєкти"
        homePage.header
                .hoverHeaderLink("Проєкти")
                .clickHeaderDropdownLink("SkyUp CityBreaks")
                .checkUrl(Routs.SKY_UP_CITY_BREAKS.getRout())
                .hoverHeaderLink("Проєкти")
                .clickHeaderDropdownLink("SkyUp Weekend for U")
                .checkUrl(Routs.SKY_UP_WEEKEND_FOR_U.getRout())
                .hoverHeaderLink("Проєкти")
                .clickHeaderDropdownLink("SkyUp Cargo")
                .checkUrl(Routs.SKY_UP_CARGO.getRout());
    }

    @Test
    public void test_4_checkLogo() {
        homePage.closeWelcomeModalIfPresent();
        homePage.header.checkLogoIsVisible();
    }


    @Test
    public void test_5_checkCurrencyButton() {
        homePage.closeWelcomeModalIfPresent();

        homePage.header
                .checkMainCurrencyButtonText("₴")
                .clickMainCurrencyButton()
                .checkCurrencyPopUpButtonsText("€", "$")
                .selectCurrency("€")
                .checkMainCurrencyButtonText("€")
                .clickMainCurrencyButton()
                .checkCurrencyPopUpButtonsText("₴", "$")
                .selectCurrency("$")
                .checkMainCurrencyButtonText("$")
                .clickMainCurrencyButton()
                .checkCurrencyPopUpButtonsText("₴", "€");
    }

    @Test
    public void test_6_buyAirTicket() {
        homePage.closeWelcomeModalIfPresent();

        homePage.header
                .clickMainLanguageButton()
                .selectLanguage("ENG");

        homePage.airWaySelector
                .clickDepartureCity()
                .selectCity("Kyiv")
                .checkDepartureCityTitle("Kyiv")
                .clickArrivalCity()
                .selectCity("Dubai")
                .checkArrivalCityTitle("Dubai");

        homePage.acceptCookies();

        FlightDate departureFlightDate = homePage.airWaySelector
                .clickForwardDatePicker()
                .selectAvailableDay();

        FlightDate returnFlightDate = homePage.airWaySelector
                .clickBackwardCityDatePicker()
                .selectAvailableDay();

        FlightOptionsPage flightOptionsPage = homePage.airWaySelector
                .clickShowFlightsButton();

        WhoIsGoingToFlyPage whoIsGoingToFlyPage = flightOptionsPage
                .checkDepartureFlightTitle("Kyiv", "Dubai")
                .checkReturnFlightTitle("Dubai", "Kyiv")
                .checkDepartureFlightDate(departureFlightDate)
                .checkReturnFlightDate(returnFlightDate)
                .setRandomDepartureFlightOption()
                .setReturnRandomFlightOption()
                .clickNextButton();

        SeatSelectionPage seatSelectionPage = whoIsGoingToFlyPage
                .typeFirstName("Vitos")
                .typeLastName("Levashov")
                .setDayOfBirth("22")
                .setMonthOfBirth("March")
                .setYearOfBirth("1984")
                .setGender("Male")
                .setCitizen("Ukraine")
                .setPassportNumber("ВР123456")
                .setPassportExpirationDay("15")
                .setPassportExpirationMonth("August")
                .setPassportExpirationYear("2025")
                .selectSeat();

        whoIsGoingToFlyPage = seatSelectionPage
                .selectForwardFlightSeat()
                .openBackWardFlyTab()
                .selectBackwardFlightSeat()
                .clickContinueButton();

        YourTicketPage yourTicketPage = whoIsGoingToFlyPage
                .clickNextButton()
                .closeStarveModalWindow();

        PaymentPage paymentPage = yourTicketPage
                .setEmail("testmail@gmail.com")
                .repeatEmail("testmail@gmail.com")
                .setPhoneNumber("0991234567")
                .clickIAgreeCheckbox()
                .clickPayButton();

        paymentPage
                .setCardNumber("1234567890123214")
                .setCardExpirationDate("01/25")
                .setCardCvvCode("123");
    }
}
