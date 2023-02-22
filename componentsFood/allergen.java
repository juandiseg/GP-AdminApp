package componentsFood;

public class allergen {

    private final int id;
    private String name;

    public allergen(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof allergen))
            return false;
        allergen temp = (allergen) obj;
        if (temp.getId() == id)
            return true;
        else
            return false;
    }
}
