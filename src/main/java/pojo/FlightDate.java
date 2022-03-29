package pojo;

public class FlightDate {
    private String day;
    private String month;

    public FlightDate(String day, String month) {
        this.day = day;
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "FlightDate{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
