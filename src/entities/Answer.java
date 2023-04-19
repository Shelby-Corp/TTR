package entities;

public class Answer extends Entity{
    public Integer questionId;
    public String text;
    public Boolean isRight;
    public Answer(Integer id, Integer questionId, String text, Boolean isRight){
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isRight = isRight;
    }

    public Boolean getRight() {
        return isRight;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getText(){
        return text;
    }

    public Boolean getIsRight(){
        return isRight;
    }
}
