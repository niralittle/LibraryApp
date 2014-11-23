package controller.client;

import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.User;

public class LoginController {
    public static User getUserObject(String login) {
        return new UserDAOImpl().getUserByLogin(login);
    }
}
