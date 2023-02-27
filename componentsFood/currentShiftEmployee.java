package componentsFood;

public class currentShiftEmployee {

    private String name;
    private String role;
    private String salary;
    private String weeklyHours;
    private String inTime;
    private String outTime;

    public currentShiftEmployee(String name, String role, Float salary, String weeklyHours, String inTime,
            String outTime) {
        this.name = name;
        this.role = role;
        this.salary = Float.toString(salary);
        this.weeklyHours = weeklyHours;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getSalary() {
        return salary;
    }

    public String getWeeklyHours() {
        return weeklyHours;
    }

    public String getTimeIn() {
        return inTime;
    }

    public String getTimeout() {
        return outTime;
    }

}
