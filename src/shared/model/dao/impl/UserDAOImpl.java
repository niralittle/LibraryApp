package shared.model.dao.impl;

import shared.model.dao.UserDAO;
import shared.model.vo.User;

import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> getByQuery(int page, int size, Map<String, String> params) {
        return null;
    }

    @Override
    public int getIdByName(String user) {
        return 0;
    }
}
