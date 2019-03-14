package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.web.email.ssl.SenderSSl;
import ua.nure.samoylenko.exception.AppException;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;
import ua.nure.samoylenko.web.service.UserService;
import ua.nure.samoylenko.utils.Validator;
import ua.nure.samoylenko.utils.VerifyRecaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Registration")
public class CreateAccountServlet extends HttpServlet {
    private RegisterDTO registerDTO;
    private StudentService studentService;
    private UserService userService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
        studentService = servicesContainer.getStudentService();
        registerDTO = new RegisterDTO();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        Validator validator = new Validator();
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password1");
        String firstName = httpServletRequest.getParameter("firstName");
        String secondName = httpServletRequest.getParameter("secondName");
        String gRecaptchaResponse = httpServletRequest.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        SenderSSl senderSSl = new SenderSSl("mailForTestingEpam@gmail.com", "Qwerty12!");

        String subject = firstName + " " + secondName + " Welcome to TestYourself service";
        String message = "You are registered as a student. After entering, you can choose a test,\n" +
                "on which you want to pass. To enter the service, use the following parameters:\n" +
                "login: " + email + "\n" +
                "password: " + password;


        registerDTO.setEmail(email);
        registerDTO.setPassword(password);
        registerDTO.setFirstName(firstName);
        registerDTO.setSecondName(secondName);

        if (!validator.validateEmailAndPasswordFromRegForm(registerDTO)) {
            throw new AppException("Invalid login or password! The password must contain at least one number and a uppercase letter. All parameters " +
                    "must be an English");
        }

        if (userService.isUserExist(registerDTO)) {
            throw new AppException("This user has already been created!");
        }

        if (!validator.validatePasswordsFormsEquals(httpServletRequest)) {
            throw new AppException("Both passwords must be equals!");
        }

        if (!validator.validateNamesFromRegForm(registerDTO)) {
            throw new AppException("You must fill first name and second name fields!");
        }
        if (!verify) {
            throw new AppException("reCAPTCHA is not confirmed");
        }

        userService.createUser(registerDTO);
        studentService.createStudent(registerDTO);
        senderSSl.send(subject, message, "mailForTestingEpam@gmail.com", email);

        httpServletResponse.sendRedirect("Registration");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("startPage.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
