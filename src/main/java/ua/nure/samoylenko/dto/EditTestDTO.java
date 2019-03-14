package ua.nure.samoylenko.dto;

public class EditTestDTO {

    private Integer id;

    private String complexityName;

    private String testName;

    private Integer time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "EditTestDTO{" +
                "id=" + id +
                ", complexityName='" + complexityName + '\'' +
                ", testName='" + testName + '\'' +
                ", time=" + time +
                '}';
    }
}
