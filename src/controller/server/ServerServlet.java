package controller.server;

import controller.client.ClientController;
import shared.model.vo.User;
import shared.utils.UtilityConstants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by niralittle on 30.11.2014.
 */

public class ServerServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        String login = req.getParameter(UtilityConstants.LOGIN);
        String password = req.getParameter(UtilityConstants.PASSWORD);
        User user;
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            System.out.println("llalla");
            System.out.println(ClientController.getInstance());
            user = ClientController.getInstance().authorize(login, password);
            if (user != null) {
                session.setAttribute(UtilityConstants.USER, user);
                if (user.isAdmin()) {
                    session.setAttribute(UtilityConstants.ORDERS, ClientController.getInstance().getOEData());
                } else {
                    System.out.println("Lalala");
                    System.out.println(ClientController.getInstance().getBookCatalogData());
                    session.setAttribute(UtilityConstants.BOOKS, ClientController.getInstance().getBookCatalogData());
                }
            }
        }
        resp.sendRedirect("LibraryApp.jsp");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");

    }
}
