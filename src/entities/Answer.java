package entities;

public class Answer extends Entity{
    public Integer questionId;
    public String text;
    public Integer isRight;
    
    public Answer(Integer id, Integer questionId, String text, Integer isRight){
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isRight = isRight;
    }


    public Integer getQuestionId() {
        return questionId;
    }

    public String getText(){
        return text;
    }

    public Integer getIsRight(){
        return isRight;
    }
}
