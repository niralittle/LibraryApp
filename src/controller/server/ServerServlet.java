package controller.server;

import controller.client.ClientController;
import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static shared.utils.UtilityConstants.*;

/**
 * Created by niralittle on 30.11.2014.
 */

public class ServerServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        User user;
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            user = ClientController.getInstance().authorize(login, password);
            if (user != null) {
                session.setAttribute(USER, user);
                if (user.isAdmin()) {
                    session.setAttribute(ORDERS, ClientController.getInstance().getOEData());
                } else {
                    session.setAttribute(BOOKS, ClientController.getInstance().getBookCatalogData());
                }
            }
        } else {
            List<String> bookIds = Arrays.asList(req.getParameterValues("book"));
            List<Book> orderedBooks = new LinkedList<>();
            for (Book book: (List<Book>) session.getAttribute(BOOKS)) {
                if (bookIds.contains(Integer.toString(book.getId()))) {
                    orderedBooks.add(book);
                }
            }
            if (!orderedBooks.isEmpty()) {
                OrderEntry entry = new OrderEntry();
                entry.setBooks(orderedBooks);
                entry.setUserId(((User) session.getAttribute(USER)).getId());
                ClientController.getInstance().createNewOrder(entry);
            }
        }
        resp.sendRedirect("LibraryApp.jsp");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");

    }
}
