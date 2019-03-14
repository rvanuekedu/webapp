package ua.nure.samoylenko.dto;

public class TestDTO {

    private Integer id;

    private Integer subjectId;

    private String subjectName;

    private String complexityName;

    private String testName;

    private Integer time;

    private Integer numberOfQuestions;

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getComplexityName() {
        return complexityName;
    }

    public void setComplexityName(String complexityName) {
        this.complexityName = complexityName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", complexityName='" + complexityName + '\'' +
                ", testName='" + testName + '\'' +
                ", time=" + time +
                ", numberOfQuestions=" + numberOfQuestions +
                '}';
    }
}
