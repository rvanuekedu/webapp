package ua.nure.samoylenko.utils;

public class RegExpConstants {
    private RegExpConstants() {
        // TODO: 13.03.2019 error 
    }
    public static final String EMAIL_VALIDATE = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)$";
    public static final String PASSWORD_VALIDATE = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$";



}
