package shared.model.dao.impl;

import shared.model.DBManager;
import shared.model.dao.DAO;
import shared.model.vo.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by niralittle on 26.10.2014.
 */
public class BookDAOImpl implements DAO<Book> {

    @Override
    public Book findById(int id) {
        String query = "SELECT * FROM BOOK WHERE ID = " + id;
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setAuthors(rs.getString(3));
                b.setDescription(rs.getString(4));
                b.setRating(rs.getInt(5));
                b.setNumberOfPages(rs.getInt(6));
                b.setCategory(rs.getString(7));
                return b;
            }
        } catch (SQLException e) {
            System.out.println(BookDAOImpl.class.getSimpleName() + ": SQL Error: " + e);
        }
        return null;
    }

    public List<Book> getByQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM BOOK");

        if (params != null && !params.isEmpty()) {
            query.append(" WHERE ");
            boolean notFirst = false; //not adding " AND " before the first param
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (notFirst) query.append(" AND ");
                if (entry.getValue() != null) {
                    query.append(entry.getKey()).append(" LIKE ?");
                } else {
                    query.append(entry.getKey()).append(" IS NULL");
                }
                notFirst = true;
            }
        }
        ResultSet rs;
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(query.toString());
            rs = statement.executeQuery();
            List<Book> result = new LinkedList<>();
            while (rs.next()) {
                result.add(new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7)));
            }
            rs.close();
            return result;
        } catch (SQLException se) {
            System.out.println(BookDAOImpl.class.getSimpleName() + ": SQL Error: " + se);
            return Collections.emptyList();
        }
    }


}
