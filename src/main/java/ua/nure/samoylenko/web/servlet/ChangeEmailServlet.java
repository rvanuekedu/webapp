package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.ChangeEmailDTO;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.exception.AppException;
import ua.nure.samoylenko.utils.Validator;
import ua.nure.samoylenko.web.email.ssl.SenderSSl;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeEmail")
public class ChangeEmailServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        Validator validator = new Validator();
        ChangeEmailDTO changeEmailDTO = new ChangeEmailDTO();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String currentEmail = httpServletRequest.getParameter("currentEmail");
        String newEmail = httpServletRequest.getParameter("newEmail");
        String password = httpServletRequest.getParameter("password");
        SenderSSl senderSSl = new SenderSSl("mailForTestingEpam@gmail.com", "Qwerty12!");

        String subject = "TestYourself service";
        String message = "You are change email." + " To enter the service, use the following parameters:\n" +
                "login: " + newEmail + "\n" +
                "password: " + user.getPassword();


        if (!user.getEmail().equals(currentEmail)) {
            throw new AppException("Current email is not valid");
        }

        if ("".equals(newEmail) || !validator.validateEmail(newEmail)) {
            throw new AppException("New email is not valid");
        }

        if (userService.checkUsersDuplicate(newEmail)) {
            throw new AppException("User with this email already exists");
        }

        if (!user.getPassword().equals(password)) {
            throw new AppException("Wrong password");
        }

        changeEmailDTO.setCurrentEmail(currentEmail);
        changeEmailDTO.setNewEmai(newEmail);
        userService.changeUserEmail(changeEmailDTO);

        user.setEmail(newEmail);
        httpServletRequest.getSession().setAttribute("user", user);

        senderSSl.send(subject, message, "mailForTestingEpam@gmail.com", user.getEmail());
        httpServletResponse.sendRedirect("ChangeEmail");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("Settings");
    }
}
