package ua.nure.samoylenko.dto;

import java.sql.Date;

public class ResultShowDTO {

    private Integer testId;

    private Integer studentId;

    private String testSubject;

    private String testComplexity;

    private String TestName;

    private Integer valueOfResult;

    private Date date;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }

    public String getTestComplexity() {
        return testComplexity;
    }

    public void setTestComplexity(String testComplexity) {
        this.testComplexity = testComplexity;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public Integer getValueOfResult() {
        return valueOfResult;
    }

    public void setValueOfResult(Integer valueOfResult) {
        this.valueOfResult = valueOfResult;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
