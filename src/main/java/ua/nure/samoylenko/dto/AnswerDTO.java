package ua.nure.samoylenko.dto;

public class AnswerDTO {

    private Integer questionId;

    private String aText;

    private boolean isCorrect;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getaText() {
        return aText;
    }

    public void setaText(String aText) {
        this.aText = aText;
    }

    public boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

}
