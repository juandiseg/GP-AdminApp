package componentsFood;

public class shift {

    private final int employeeID;
    private String date;
    private String startTime;
    private String endTime;
    private String realStartTime = null;
    private String realEndTime = null;
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

    public void setDate(String newDate) {
        date = newDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String newStart) {
        startTime = newStart;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String newEnd) {
        endTime = newEnd;
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

    public boolean equals(Object o) {
        if (!(o instanceof shift))
            return false;
        shift temp = (shift) o;
        if (temp.getEmployeeId() == employeeID && temp.getDate().equals(date) && temp.getStartTime().equals(startTime)
                && temp.getEndTime().equals(endTime))
            return true;
        return false;

    }
}
