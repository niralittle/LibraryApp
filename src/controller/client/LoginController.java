package controller.client;

import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.User;

public class LoginController {
    public static User getUserObject(String login, String password) {
        User user = new UserDAOImpl().getUserByLogin(login);
        if (user!=null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }

    }
}
