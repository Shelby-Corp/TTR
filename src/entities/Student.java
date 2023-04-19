package entities;

public class Student extends Entity {
    public String name;
    public Student(Integer id, String name){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
