package shared.model.dao.impl;

import shared.model.DBManager;
import shared.model.dao.DAO;
import shared.model.vo.Book;
import shared.model.vo.EntityWithId;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 28.10.2014.
 */
public class OrderEntryDAOImpl implements DAO {

    @Override
    public EntityWithId findById(int id) {
        return null;
    }

    @Override
    public List<EntityWithId> getByQuery(int page, int size, Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        query.append("\t INSERT * \n\t INTO TABLE ORDER_ENTRY( ");

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
            Statement statement = DBManager.getConnection().prepareStatement(query.toString());
            rs = statement.executeQuery(query.toString());
            List<EntityWithId> result = new LinkedList<EntityWithId>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthors(rs.getString(3));
                book.setDescription(rs.getString(4));
                book.setRating(rs.getInt(5));
                book.setNumberOfPages(rs.getInt(6));
                book.setCategory(rs.getString(7));
                result.add(book);
            }
            statement.close();
            return result;
        } catch (SQLException se) {
            System.out.println("SQL Error: " + se);
            return Collections.EMPTY_LIST;
        }
    }
}
