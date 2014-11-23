package shared.model.dao.impl;

import shared.model.DBManager;
import shared.model.dao.DAO;
import shared.model.vo.Book;
import shared.model.vo.OrderEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 28.10.2014.
 */
public class OrderEntryDAOImpl implements DAO<OrderEntry> {

    public OrderEntry findById(int id) {
        return null;
    }

    public List<OrderEntry> getByQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ID FROM TABLE ORDER_ENTRY ");

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
            List<OrderEntry> result = new LinkedList<>();
            while (rs.next()) {
                result.add(getEntryById(rs.getInt(1)));
            }
            statement.close();
            return result;
        } catch (SQLException se) {
            System.out.println("SQL Error: " + se);
            return Collections.emptyList();
        }
    }

    public OrderEntry getEntryById(int id) {
        OrderEntry result = new OrderEntry();
        try {
            PreparedStatement entity = DBManager.getConnection()
                    .prepareStatement("SELECT ID, USERID, WAITINGSINCE  FROM ORDER_ENTRY WHERE ID=" + id);
            ResultSet rs = entity.executeQuery();
            if (rs.next()) {
                result.setId(rs.getInt(1));
                result.setUserId(rs.getInt(2));
                result.setWaitingSince(rs.getDate(3));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
            return null;
        }
        BookDAOImpl bookDAO = new BookDAOImpl();
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement booksStatement = DBManager.getConnection()
                    .prepareStatement("SELECT BOOKID FROM OE_BOOK WHERE ID=" + id);
            ResultSet rs = booksStatement.executeQuery();
            while (rs.next()) {
                books.add(bookDAO.findById(rs.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
            return null;
        }
        result.setBooks(books);
        return result;
    }
}
