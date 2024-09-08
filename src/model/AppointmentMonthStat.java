package model;

public class AppointmentMonthStat {
    private String month;
    private int total;
    private String type;

    /**
     * This is the constructor for the AppointmentMonthStat class
     * @param month
     * @param total
     * @param type
     */
    public AppointmentMonthStat(String month, int total, String type){
        this.month = month;
        this.total = total;
        this.type = type;
    }
    /**
     * This is the getter for the AppointmentMonth
     * @return
     */

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

    public String getType() {
        return type;
    }

    public void setAppointmentType(String type) {
        this.type = type;
    }
}
