package entities;

public class Picture {
    public Integer questionId;

    public Integer numberOfPicture;

    public String imagePath;

    public Picture(Integer questionId, Integer numberOfPicture, String imagePath){
        this.questionId = questionId;
        this.numberOfPicture = numberOfPicture;
        this.imagePath = imagePath;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Integer getNumberOfPicture() {
        return numberOfPicture;
    }
}
