package ua.nure.samoylenko.utils;

import org.apache.commons.lang3.StringUtils;
import ua.nure.samoylenko.dto.RegisterDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.samoylenko.utils.RegExpConstants.EMAIL_VALIDATE;
import static ua.nure.samoylenko.utils.RegExpConstants.PASSWORD_VALIDATE;

public class Validator {

    public boolean validatePasswordsFormsEquals(HttpServletRequest request) {
        String passwordField1 = request.getParameter("password1");
        String passwordField2 = request.getParameter("password2");
        return passwordField1.equals(passwordField2);
    }

    public boolean validateNamesFromRegForm(RegisterDTO registerDTO) {
        return StringUtils.isBlank(registerDTO.getSecondName()) && StringUtils.isBlank(registerDTO.getFirstName());
    }

    public boolean validateEmailAndPasswordFromRegForm(RegisterDTO registerDTO) {
        return validateEmail(registerDTO.getEmail()) && validatePassword(registerDTO.getPassword());
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_VALIDATE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_VALIDATE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
