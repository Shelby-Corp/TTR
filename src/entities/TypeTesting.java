package entities;

public class TypeTesting extends Entity{
    String description;
    public TypeTesting(Integer id, String description){
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
