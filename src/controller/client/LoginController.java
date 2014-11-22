package controller.client;

public class LoginController {
    public static boolean verifyLoginPassword(String login, String password) {
        return login.equals(password);
    }
}
