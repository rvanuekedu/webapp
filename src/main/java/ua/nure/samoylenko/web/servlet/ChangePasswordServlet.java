package ua.nure.samoylenko.web.servlet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.samoylenko.dto.ChangePasswordDTO;
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

@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
    private UserService userService;
    private static Logger LOGGER = Logger.getLogger(ChangePasswordServlet.class);

    @Override
    public void init() {
        LOGGER.debug("Init servlet AddNewTopic start");
        ServicesContainer servicesContainer = (ServicesContainer) getServletContext().getAttribute("servicesContainer");
        userService = servicesContainer.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do post in servlet ChangePassword start");
        httpServletRequest.setCharacterEncoding("utf-8");
        Validator validator = new Validator();
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String currentPassword = httpServletRequest.getParameter("currentPassword");
        String newPassword = httpServletRequest.getParameter("password1");
        SenderSSl senderSSl = new SenderSSl("mailForTestingEpam@gmail.com", "Qwerty12!");
        String sha1Password = DigestUtils.shaHex(currentPassword);

        LOGGER.debug("Set the subject and message that will be sent to user email");
        String subject = "TestYourself service";
        String message = "You are change password." + " To enter the service, use the following parameters:\n" +
                "login: " + user.getEmail() + "\n" +
                "password: " + newPassword;

        LOGGER.debug("Check the validity of the received data");
        if (!user.getPassword().equals(sha1Password)) {
            throw new AppException("Current password is not valid");
        }

        if (StringUtils.EMPTY.equals(newPassword) || !validator.validatePassword(newPassword)) {
            throw new AppException("Password is not valid");
        }

        if (!validator.validatePasswordsFormsEquals(httpServletRequest)) {
            throw new AppException("Both passwords must be equals!");
        }

        changePasswordDTO.setUserEmail(user.getEmail());
        changePasswordDTO.setNewPassword(newPassword);
        userService.changeUserPassword(changePasswordDTO);
        String changedPassword = userService.getUser(user.getEmail()).getPassword();
        user.setPassword(changedPassword);
        httpServletRequest.getSession().setAttribute("user", user);
        LOGGER.debug("Trying to send email to user email");
        senderSSl.send(subject, message, "mailForTestingEpam@gmail.com", user.getEmail());
        LOGGER.debug("Trying to send redirect in ChangePassword");
        httpServletResponse.sendRedirect("ChangePassword");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.debug("Do get in servlet ChangePassword start");
        httpServletResponse.sendRedirect("Settings");
    }
}
