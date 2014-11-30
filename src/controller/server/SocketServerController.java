package controller.server;

import shared.model.dao.OrderEntryDAO;
import shared.model.dao.UserDAO;
import shared.model.dao.impl.BookDAOImpl;
import shared.model.dao.impl.OrderEntryDAOImpl;
import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;
import shared.utils.UtilityConstants;
import shared.utils.PingPong;

import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 23.11.2014.
 */


public class SocketServerController {


    public static PingPong.ServerResponse processRequest(PingPong.ClientRequest request) {

        PingPong.ServerResponse response = new PingPong.ServerResponse();
        Map<String, Object> params = request.getParams();
        UtilityConstants.Command requestedCommand = (UtilityConstants.Command) params.get(UtilityConstants.COMMAND);

        switch (requestedCommand) {
            case CHECK_CREDENTIALS:
                String login = (String) params.get(UtilityConstants.LOGIN);
                String password = (String) params.get(UtilityConstants.PASSWORD);
                response.addParam(UtilityConstants.USER, SecurityCheckOnServer.checkCredentials(login, password));
                break;
            case GET_BOOK_CATALOG:
                BookDAOImpl bookDAO = new BookDAOImpl();
                List<Book> books = bookDAO.getByQuery(null);
                response.addParam(UtilityConstants.BOOKS, books);
                break;
            case ADD_ORDER:
                OrderEntry entry = (OrderEntry) params.get(UtilityConstants.NEW_ENTRY);
                OrderEntryDAO entryDAO = new OrderEntryDAOImpl();
                entryDAO.createOrder(entry);
                response.addParam(UtilityConstants.SUCCESS, null);
                break;
            case GET_ORDERS:
                OrderEntryDAO entryDAO1 = new OrderEntryDAOImpl();
                response.addParam(UtilityConstants.ORDERS, entryDAO1.getByQuery(null));
                break;
            default:
                response.addParam(UtilityConstants.ERROR, "Unsupported command");
        }
        return response;
    }

    private static class SecurityCheckOnServer {
        public static User checkCredentials(String login, String password) {
            UserDAO dao = new UserDAOImpl();
            User user = dao.getUserByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;
        }
    }
}

