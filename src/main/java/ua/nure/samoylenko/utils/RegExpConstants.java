package ua.nure.samoylenko.utils;

class RegExpConstants {
    private RegExpConstants() {
        throw new UnsupportedOperationException("Util method");
    }

    static final String EMAIL_VALIDATE = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)$";
    static final String PASSWORD_VALIDATE = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$";


}
