package ua.nure.samoylenko.utils;

import org.junit.Before;
import org.junit.Test;
import ua.nure.samoylenko.dto.RegisterDTO;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    private static final String CORRECT_EMAIL = "Illia_Samoylenko@gmail.com";
    private static final String INCORRECT_EMAIL = "qwe";
    private static final String CORRECT_PASSWORD = "!1qweQWE";
    private static final String INCORRECT_PASSWORD = "1";
    private Validator validator;
    private RegisterDTO registerDTO;

    @Before
    public void init() {
        validator = new Validator();
        registerDTO = new RegisterDTO();
    }

    @Test
    public void shouldReturnTrue_whenPasswordCorrect() {
        assertTrue(validator.validatePassword(CORRECT_PASSWORD));
    }

    @Test
    public void shouldReturnFalse_whenPasswordIncorrect() {
        assertFalse(validator.validatePassword(INCORRECT_PASSWORD));
    }

    @Test
    public void shouldReturnTrue_whenEmailCorrect() {
        assertTrue(validator.validateEmail(CORRECT_EMAIL));
    }

    @Test
    public void shouldReturnFalse_whenEmailIncorrect() {
        assertFalse(validator.validateEmail(INCORRECT_EMAIL));
    }

    @Test
    public void shouldReturnTrue_whenEmailAndPasswordAreCorrect() {
        registerDTO.setEmail(CORRECT_EMAIL);
        registerDTO.setPassword(CORRECT_PASSWORD);
        assertTrue(validator.validateEmailAndPasswordFromRegForm(registerDTO));
    }

    @Test
    public void shouldReturnFalse_whenEmailAndPasswordAreIncorrect() {
        registerDTO.setEmail(INCORRECT_EMAIL);
        registerDTO.setPassword(INCORRECT_PASSWORD);
        assertFalse(validator.validateEmailAndPasswordFromRegForm(registerDTO));
    }

    @Test
    public void shouldReturnFalse_whenEmailIsIncorrectAndPasswordIsCorrect() {
        registerDTO.setEmail(INCORRECT_EMAIL);
        registerDTO.setPassword(CORRECT_PASSWORD);
        assertFalse(validator.validateEmailAndPasswordFromRegForm(registerDTO));
    }

    @Test
    public void shouldReturnFalse_whenEmailIsCorrectAndPasswordIsIncorrect() {
        registerDTO.setEmail(CORRECT_EMAIL);
        registerDTO.setPassword(INCORRECT_PASSWORD);
        assertFalse(validator.validateEmailAndPasswordFromRegForm(registerDTO));
    }

    @Test
    public void shouldReturnFalse_whenNameFieldsAreCorrect() {
        registerDTO.setFirstName("Illia");
        registerDTO.setSecondName("Somoylenko");
        assertFalse(validator.validateNamesFromRegForm(registerDTO));
    }

    @Test
    public void shouldReturnTrue_whenNameFieldAreIncorrect() {
        registerDTO.setFirstName(EMPTY);
        registerDTO.setSecondName(EMPTY);
        assertTrue(validator.validateNamesFromRegForm(registerDTO));
    }
}