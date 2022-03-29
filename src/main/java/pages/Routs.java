package pages;

public enum Routs {
    MY_TICKETS("my-ticket"),
    ROUTES_MAP("routes-map"),
    ALL_FLIGHTS("best"),
    CAREER("career"),
    CONTACTS("contact"),
    FAQ("faq"),
    NEWS("news"),
    OUR_FLEET("fleet"),
    OUR_DIRECTIONS("our-directions"),
    SKY_UP_CITY_BREAKS("city-breaks"),
    SKY_UP_WEEKEND_FOR_U("weekend-for-u"),
    SKY_UP_CARGO("cargo.skyup.aero/");

    private final String rout;

    Routs(String rout) {
        this.rout = rout;
    }

    public String getRout() {
        return rout;
    }
}
