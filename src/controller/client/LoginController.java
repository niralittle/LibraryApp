package controller.client;

public class LoginController {
    public static boolean verifyLoginPassword(String user, String password) {
        if (user.isEmpty() && password.isEmpty()) {
            return false;
        } else {
            return user.equals(password);
        }
    }
}
