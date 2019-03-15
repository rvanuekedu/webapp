package ua.nure.samoylenko.utils;

import org.apache.commons.lang3.StringUtils;
import ua.nure.samoylenko.dto.RegisterDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean validatePasswordsFormsEquals(HttpServletRequest request) {
        String passwordField1 = request.getParameter("password1");
        String passwordField2 = request.getParameter("password2");
        return passwordField1.equals(passwordField2);
    }

    public boolean validateNamesFromRegForm(RegisterDTO registerDTO) {
        return !StringUtils.isBlank(registerDTO.getSecondName()) && !StringUtils.isBlank(registerDTO.getFirstName());
    }

    public boolean validateEmailAndPasswordFromRegForm(RegisterDTO registerDTO) {
        boolean emailFlag = false;
        boolean passwordFlag = false;
        Pattern pattern = Pattern.compile(RegExpConstants.EMAIL_VALIDATE);
        Matcher matcher = pattern.matcher(registerDTO.getEmail());

        if (matcher.find()) {
            emailFlag = true;
        }
        pattern = Pattern.compile(RegExpConstants.PASSWORD_VALIDATE);
        matcher = pattern.matcher(registerDTO.getPassword());

        if (matcher.find()) {
            passwordFlag = true;
        }
        return emailFlag && passwordFlag;
    }

    public boolean validateEmail(String email) {
        boolean emailFlag = false;

        Pattern pattern = Pattern.compile(RegExpConstants.EMAIL_VALIDATE);
        Matcher matcher = pattern.matcher(email);

        if (matcher.find()) {
            emailFlag = true;
        }
        return emailFlag;
    }

    public boolean validatePassword(String password) {
        boolean passwordFlag = false;
        Pattern pattern = Pattern.compile(RegExpConstants.PASSWORD_VALIDATE);
        Matcher matcher = pattern.matcher(password);

        if (matcher.find()) {
            passwordFlag = true;
        }
        return passwordFlag;
    }
}
