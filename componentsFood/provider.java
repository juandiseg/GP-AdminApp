package componentsFood;

public class provider {

    private final int id;
    private String name;
    private String email;

    public provider(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof provider))
            return false;
        provider temp = (provider) obj;
        if (temp.getId() == id && temp.getName() == name && temp.getEmail() == email)
            return true;
        else
            return false;
    }
}
