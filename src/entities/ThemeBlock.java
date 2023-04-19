package entities;

public class ThemeBlock extends Entity {
    public String name;
    public String description;

    public ThemeBlock(Integer id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
