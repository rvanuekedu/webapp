package ua.nure.samoylenko.dto;

import java.sql.Date;

public class ResultDTO {

    Integer valueOfResult;

    Date date;

    String testName;

    String dateToDB;

    Integer testId;

    Integer studentId;

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

    public String getDateToDB() {
        return dateToDB;
    }

    public void setDateToDB(String dateToDB) {
        this.dateToDB = dateToDB;
    }

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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "valueOfResult=" + valueOfResult +
                ", date=" + date +
                ", testName='" + testName + '\'' +
                ", dateToDB='" + dateToDB + '\'' +
                ", testId=" + testId +
                ", studentId=" + studentId +
                '}';
    }
}
