package entities;

import java.util.Objects;

public class Question extends Entity{
    String text;

    public Question(Integer id, String text){
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Question &&
                Objects.equals(((Question) o).id, this.id) &&
                ((Question) o).text.equals(this.text);
    }
}
