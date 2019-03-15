package ua.nure.samoylenko.web.servlet;

import ua.nure.samoylenko.dto.ChangePasswordDTO;
import ua.nure.samoylenko.entities.User;
import ua.nure.samoylenko.exception.AppException;
import ua.nure.samoylenko.utils.Validator;
import ua.nure.samoylenko.web.email.ssl.SenderSSl;
import ua.nure.samoylenko.web.service.ServicesContainer;
import ua.nure.samoylenko.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("utf-8");
        Validator validator = new Validator();
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String currentPassword = httpServletRequest.getParameter("currentPassword");
        String newPassword = httpServletRequest.getParameter("password1");
        SenderSSl senderSSl = new SenderSSl("mailForTestingEpam@gmail.com", "Qwerty12!");

        String subject = "TestYourself service";
        String message = "You are change password." + " To enter the service, use the following parameters:\n" +
                "login: " + user.getEmail() + "\n" +
                "password: " + newPassword;

        if (!user.getPassword().equals(currentPassword)) {
            throw new AppException("Current password is not valid");
        }

        if ("".equals(newPassword) || !validator.validatePassword(newPassword)) {
            throw new AppException("Password is not valid");
        }

        if (!validator.validatePasswordsFormsEquals(httpServletRequest)) {
            throw new AppException("Both passwords must be equals!");
        }

        changePasswordDTO.setUserEmail(user.getEmail());
        changePasswordDTO.setNewPassword(newPassword);
        userService.chanheUserPassword(changePasswordDTO);

        user.setPassword(newPassword);
        httpServletRequest.getSession().setAttribute("user", user);

        senderSSl.send(subject, message, "mailForTestingEpam@gmail.com", user.getEmail());
        httpServletResponse.sendRedirect("ChangePassword");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.sendRedirect("Settings");
    }
}
