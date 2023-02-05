package componentsFood;

public class employee {
    private final int id;
    private String name;
    private float salary;
    private String hoursWeek;
    private int role_id;
    private boolean active;

    public employee(int id, String name, float salary, String hoursWeek, int role_id, boolean active) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hoursWeek = hoursWeek;
        this.role_id = role_id;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public String getHoursWeek() {
        return hoursWeek;
    }

    public int getRoleID() {
        return role_id;
    }

    public boolean getActive() {
        return active;
    }

    public String toString() {
        return name;
    }
}
