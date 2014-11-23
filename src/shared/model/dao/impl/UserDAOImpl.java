package shared.model.dao.impl;

import shared.model.DBManager;
import shared.model.dao.UserDAO;
import shared.model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findById(int id) {
        String query = "SELECT * FROM LIB_USER WHERE ID = " + id;
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        }
        return null;
    }

    @Override
    public List<User> getByQuery(Map<String, String> params) {
        return null;
    }

    @Override
    public int getIdByName(String user) {
        User result = getUserByLogin(user);
        return result != null ? result.getId() : 0;
    }


    @Override
    public User getUserByLogin(String user) {
        String query = "SELECT * FROM LIB_USER WHERE LOGIN = '" + user + "'";
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        }
        return null;
    }
}
