package controller.server;

import shared.model.dao.OrderEntryDAO;
import shared.model.dao.UserDAO;
import shared.model.dao.impl.BookDAOImpl;
import shared.model.dao.impl.OrderEntryDAOImpl;
import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;
import shared.utils.UtilityConstants;
import shared.utils.PingPong;

import java.util.Map;

/**
 * Created by niralittle on 23.11.2014.
 */


public class SocketServerController {


    public static PingPong.ServerResponse processRequest(PingPong.ClientRequest request) {
        System.out.println(SocketServerController.class.getSimpleName() + ": Started processing request");
        PingPong.ServerResponse response = new PingPong.ServerResponse();
        Map<String, Object> params = request.getParams();
        UtilityConstants.Command requestedCommand = (UtilityConstants.Command) params.get(UtilityConstants.COMMAND);
        System.out.println(SocketServerController.class.getSimpleName() + ": Command: " + requestedCommand.name());
        switch (requestedCommand) {
            case CHECK_CREDENTIALS:
                String login = (String) params.get(UtilityConstants.LOGIN);
                String password = (String) params.get(UtilityConstants.PASSWORD);
                response.addParam(UtilityConstants.USER, SecurityCheckOnServer.checkCredentials(login, password));
                break;
            case GET_BOOK_CATALOG:
                BookDAOImpl bookDAO = new BookDAOImpl();
                response.addParam(UtilityConstants.BOOKS, bookDAO.getByQuery(null));
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
        System.out.println(SocketServerController.class.getSimpleName() + ": Finished processing request");
        return response;
    }

    public static class SecurityCheckOnServer {
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