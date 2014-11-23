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

        PingPong.ServerResponse response = new PingPong.ServerResponse();
        Map<String, Object> params = request.getParams();
        int requestedAction = ((UtilityConstants.Command) params.get(UtilityConstants.COMMAND)).getNumber();

        switch (requestedAction) {
            case 1:
                String login = (String) params.get(UtilityConstants.LOGIN);
                String password = (String) params.get(UtilityConstants.PASSWORD);
                response.getParams().put(UtilityConstants.USER, SecurityCheckOnServer.checkCredentials(login, password));
                break;
            case 2:
                BookDAOImpl bookDAO = new BookDAOImpl();
                response.getParams().put(UtilityConstants.BOOKS, bookDAO.getByQuery(null));
                break;
            case 3:
                OrderEntry entry = (OrderEntry) params.get(UtilityConstants.NEW_ENTRY);
                OrderEntryDAO entryDAO = new OrderEntryDAOImpl();
                entryDAO.createOrder(entry);
                response.getParams().put(UtilityConstants.SUCCESS, null);
                break;
            case 4:
                OrderEntryDAO entryDAO1 = new OrderEntryDAOImpl();
                response.getParams().put(UtilityConstants.ORDERS, entryDAO1.getByQuery(null));
                break;
            default:
                response.getParams().put(UtilityConstants.ERROR, "Unsupported command");
        }
        return response;
    }

    private static class SecurityCheckOnServer {
        public static User checkCredentials(String login, String password) {
            UserDAO dao = new UserDAOImpl();
            User user = dao.getUserByLogin(login);
            if (user.getPassword().equals(password)) {
                return user;
            }
            return null;
        }
    }
}

