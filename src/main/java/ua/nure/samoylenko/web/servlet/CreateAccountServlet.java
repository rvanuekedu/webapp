package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.samoylenko.dto.RegisterDTO;
import ua.nure.samoylenko.exception.AppException;
import ua.nure.samoylenko.utils.Validator;
import ua.nure.samoylenko.utils.VerifyRecaptcha;
import ua.nure.samoylenko.web.email.ssl.SenderSSl;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.StudentService;
import ua.nure.samoylenko.web.service.UserService;

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
    private static Logger LOGGER = Logger.getLogger(CreateAccountServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet Registration start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
        studentService = servicesContainer.getStudentService();
        registerDTO = new RegisterDTO();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do post in servlet Registration start");
        httpServletRequest.setCharacterEncoding("utf-8");
        Validator validator = new Validator();
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password1");
        String firstName = httpServletRequest.getParameter("firstName");
        String secondName = httpServletRequest.getParameter("secondName");
        String gRecaptchaResponse = httpServletRequest.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        SenderSSl senderSSl = new SenderSSl("mailForTestingEpam@gmail.com", "Qwerty12!");

        LOGGER.debug("Set the subject and message that will be sent to user email");
        String subject = firstName + " " + secondName + " Welcome to TestYourself service";
        String message = "You are registered as a student. After entering, you can choose a test,\n" +
                "on which you want to pass. To enter the service, use the following parameters:\n" +
                "login: " + email + "\n" +
                "password: " + password;


        registerDTO.setEmail(email);
        registerDTO.setPassword(password);
        registerDTO.setFirstName(firstName);
        registerDTO.setSecondName(secondName);

        LOGGER.debug("Check the validity of email and password from form");
        if (!validator.validateEmailAndPasswordFromRegForm(registerDTO)) {
            throw new AppException("Invalid login or password! The password must contain at least one number and a uppercase letter. All parameters " +
                    "must be an English");
        }
        LOGGER.debug("Check is user with the same email is exists");
        if (userService.isUserExist(registerDTO)) {
            throw new AppException("This user has already been created!");
        }
        LOGGER.debug("Check the equals of both passwords");
        if (!validator.validatePasswordsFormsEquals(httpServletRequest)) {
            throw new AppException("Both passwords must be equals!");
        }
        LOGGER.debug("Check the validity of name");
        if (!validator.validateNamesFromRegForm(registerDTO)) {
            throw new AppException("You must fill first name and second name fields!");
        }
        LOGGER.debug("Check reCAPTCHA is confirmed");
        if (!verify) {
            throw new AppException("reCAPTCHA is not confirmed");
        }

        userService.createUser(registerDTO);
        studentService.createStudent(registerDTO);
        LOGGER.debug("Trying to send email to user email");
        senderSSl.send(subject, message, "mailForTestingEpam@gmail.com", email);
        LOGGER.debug("Trying to send redirect in Registration");
        httpServletResponse.sendRedirect("Registration");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in servlet Registration start");
        httpServletRequest.getRequestDispatcher("startPage.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
