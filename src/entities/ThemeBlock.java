package entities;

public class ThemeBlock extends Entity {
    public Integer questionId;
    public String name;
    public String description;

    public ThemeBlock(Integer id, Integer questionId, String name, String description){
        this.id = id;
        this.questionId = questionId;
        this.name = name;
        this.description = description;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
