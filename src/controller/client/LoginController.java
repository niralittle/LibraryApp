package controller.client;

import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.User;

public class LoginController {
    public static boolean verifyLoginPassword(String login, String password) {
        User user = new UserDAOImpl().getUserByName(login);
        return password.equals(user.getPassword());
    }
}
