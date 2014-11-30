package controller.client;

import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;
import shared.utils.PingPong;
import shared.utils.UtilityConstants;

import java.io.IOException;
import java.util.List;

/**
 * Created by niralittle on 23.11.2014.
 */
public class SocketClientController extends ClientController {

    @Override
    public User authorize(String login, String password) {
        PingPong.ClientRequest request = new PingPong.ClientRequest();
        PingPong.ServerResponse response = null;
        request.addParam(
                UtilityConstants.COMMAND,
                UtilityConstants.Command.CHECK_CREDENTIALS);
        request.addParam(UtilityConstants.LOGIN, login);
        request.addParam(UtilityConstants.PASSWORD, password);
        try {
            response = SocketClient.retrieveOnRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = null;
        if (response != null) {
            String error = (String) response.getParams().get(UtilityConstants.ERROR);
            if (error != null) {
                System.out.println("Error during authorization: " + error);
                authorize(login, password);
            }
            user = (User) response.getParams().get(UtilityConstants.USER);
        }
        return user;
    }

    @Override
    public List<Book> getBookCatalogData() {
        return null;
    }

    @Override
    public List<OrderEntry> getOEData() {
        return null;
    }

    @Override
    public void createNewOrder(OrderEntry oe) {

    }
}
