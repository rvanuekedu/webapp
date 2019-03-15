package ua.nure.samoylenko.dto;

public class ChangeEmailDTO {
    private String currentEmail;

    private String newEmai;

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }

    public String getNewEmai() {
        return newEmai;
    }

    public void setNewEmai(String newEmai) {
        this.newEmai = newEmai;
    }
}
