package ua.nure.samoylenko.web.service;

public class ServicesContainer {
    private AnswerService answerService;
    private QuestionService questionService;
    private ResultService resultService;
    private StudentService studentService;
    private SubjectService subjectService;
    private TestService testService;
    private UserService userService;

    public AnswerService getAnswerService() {
        return answerService;
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public ResultService getResultService() {
        return resultService;
    }

    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public TestService getTestService() {
        return testService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
