package ua.nure.samoylenko.entities;

public class Test {

    private Integer id;

    private String testName;

    private Integer subjectId;

    private String complexity;

    private int time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", testName=" + testName + ", subjectId=" + subjectId + ", complexity=" + complexity
                + ", time=" + time + "]";
    }

}
