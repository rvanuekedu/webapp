package ua.nure.samoylenko.dto;

public class ShowQuestionDTO {
    private String questionText;

    private String answerText;

    private Boolean isCorrect;

    private Integer questionId;

    private Integer answerQuestionId;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerQuestionId() {
        return answerQuestionId;
    }

    public void setAnswerQuestionId(Integer answerQuestionId) {
        this.answerQuestionId = answerQuestionId;
    }

    @Override
    public String toString() {
        return "ShowQuestionDTO{" +
                "questionText='" + questionText + '\'' +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                ", answerQuestionId=" + answerQuestionId +
                '}';
    }
}
