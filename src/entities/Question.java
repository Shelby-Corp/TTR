package entities;

public class Question extends Entity{
    String text;

    public Question(Integer id, String text){
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
