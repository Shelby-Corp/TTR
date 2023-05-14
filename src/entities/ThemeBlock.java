package entities;

public class ThemeBlock extends Entity {
    public String name;
    public String description;

    public ThemeBlock(Integer id, String name){
        this.id = id;
        this.name = name;
    }



    public String getName() {
        return name;
    }
}
