package ua.nure.samoylenko.entities;

public class Answer {

    private Integer answerId;

    private String answerText;

    private Boolean isCorrect;

    private Integer questionId;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
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

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                '}';
    }
}
