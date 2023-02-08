package componentsFood;

public class shift {

    private final int employeeID;
    private String date;
    private String startTime;
    private String endTime;
    private String realStartTime;
    private String realEndTime;
    private boolean undertime;

    public shift(int employeeID, String date, String startTime, String endTime) {
        this.employeeID = employeeID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.realStartTime = "";
        this.realEndTime = "";
        this.undertime = false;
    }

    public shift(int employeeID, String date, String startTime, String endTime, String realStartTime,
            String realEndTime, boolean undertime) {
        this.employeeID = employeeID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.realStartTime = realStartTime;
        this.realEndTime = realEndTime;
        this.undertime = undertime;
    }

    public int getEmployeeId() {
        return employeeID;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getRealStartTime() {
        return realStartTime;
    }

    public String getRealEndTime() {
        return realEndTime;
    }

    public boolean getIsUndertime() {
        return undertime;
    }
}
