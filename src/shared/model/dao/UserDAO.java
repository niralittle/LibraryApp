package shared.model.dao;

import shared.model.vo.User;

public interface UserDAO extends DAO<User> {

    int getIdByName(String user);
    User getUserByLogin(String user);
}
