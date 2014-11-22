package controller.client;

import shared.model.dao.UserDAO;
import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.view.desktop.MainWindow;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 28.10.2014.
 */
public abstract class BookCatalog {

    public static final String GET_ALL_BOOKS = "getAllBooks";

    private static final UserDAO USER_DAO = new UserDAOImpl();


    public static void createOrder(List<Book> books, String user) {
        OrderEntry oe = new OrderEntry();
        oe.setUserId(USER_DAO.getIdByName(user));
        oe.setBooks(books);

    }

    public static List<Book> getAllBooks() {
        Map<String, Object> request = new HashMap<>();
        request.put("method", GET_ALL_BOOKS);
        try {
            return (List<Book>) ConnectionEstablisher.retrieveOnRequest(request).getParams().get("methodResult");
        } catch (Exception e) {
            List<Book> books = new ArrayList<>();
            Book book1 = new Book();
            book1.setTitle("Title 1");
            book1.setAuthors("Author A.");
            books.add(book1);
            Book book2 = new Book();
            book2.setTitle("Title 2");
            book2.setAuthors("Author B.");
            books.add(book2);
            Book book3 = new Book();
            book3.setTitle("Title 3");
            book3.setAuthors("Author C.");
            books.add(book3);
            return books;
        }
    }


}
