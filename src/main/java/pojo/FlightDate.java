package pojo;

public class FlightDate {
    private final String DAY;
    private final String MONTH;

    public FlightDate(String day, String month) {
        this.DAY = day;
        this.MONTH = month;
    }

    public String getDay() {
        return DAY;
    }


    public String getMonth() {
        return MONTH;
    }


    @Override
    public String toString() {
        return "FlightDate{" +
                "day='" + DAY + '\'' +
                ", month='" + MONTH + '\'' +
                '}';
    }
}
