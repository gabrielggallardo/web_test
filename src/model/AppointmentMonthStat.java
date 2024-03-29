package model;

public class AppointmentMonthStat {
    private String month;
    private int total;

    /**
     * This is the constructor for the AppointmentMonthStat class
     * @param month
     * @param total
     */
    public AppointmentMonthStat(String month, int total){
        this.month = month;
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
