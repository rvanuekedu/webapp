package ua.nure.samoylenko.entities;

public class Result {

    private int id;

    private String valueOfResult;

    private String date;

    private Integer tests_id;

    private Integer students_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValueOfResult() {
        return valueOfResult;
    }

    public void setValueOfResult(String valueOfResult) {
        this.valueOfResult = valueOfResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTests_id() {
        return tests_id;
    }

    public void setTests_id(int tests_id) {
        this.tests_id = tests_id;
    }

    public Integer getStudents_id() {
        return students_id;
    }

    public void setStudents_id(Integer students_id) {
        this.students_id = students_id;
    }

    @Override
    public String toString() {
        return "Result [valueOfResult=" + valueOfResult + ", date=" + date + ", tests_id=" + tests_id + ", students_id="
                + students_id + "]";
    }

}
