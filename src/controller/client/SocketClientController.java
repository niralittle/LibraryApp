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
                System.out.println(SocketClientController.class.getSimpleName() + ": Error during authorization: " + error);
                return null;
            }
            user = (User) response.getParams().get(UtilityConstants.USER);
        }
        return user;
    }

    @Override
    public List<Book> getBookCatalogData() {
        PingPong.ClientRequest request = new PingPong.ClientRequest();
        PingPong.ServerResponse response = null;
        request.addParam(
                UtilityConstants.COMMAND,
                UtilityConstants.Command.GET_BOOK_CATALOG);
        try {
            response = SocketClient.retrieveOnRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Book> books = null;
        if (response != null) {
            String error = (String) response.getParams().get(UtilityConstants.ERROR);
            if (error != null) {
                System.out.println(SocketClientController.class.getSimpleName() + ": Error during catalog retrieving: " + error);
                return null;
            }
            books = (List<Book>) response.getParams().get(UtilityConstants.BOOKS);
        }
        return books;
    }

    @Override
    public List<OrderEntry> getOEData() {
        PingPong.ClientRequest request = new PingPong.ClientRequest();
        PingPong.ServerResponse response = null;
        request.addParam(
                UtilityConstants.COMMAND,
                UtilityConstants.Command.GET_ORDERS);
        try {
            response = SocketClient.retrieveOnRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<OrderEntry> orders = null;
        if (response != null) {
            String error = (String) response.getParams().get(UtilityConstants.ERROR);
            if (error != null) {
                System.out.println(SocketClientController.class.getSimpleName() + ": Error during OE retrieving: " + error);
                return null;
            }
            orders = (List<OrderEntry>) response.getParams().get(UtilityConstants.ORDERS);
        }
        return orders;
    }

    @Override
    public void createNewOrder(OrderEntry oe) {
        PingPong.ClientRequest request = new PingPong.ClientRequest();
        PingPong.ServerResponse response = null;
        request.addParam(
                UtilityConstants.COMMAND,
                UtilityConstants.Command.ADD_ORDER);
        request.addParam(UtilityConstants.NEW_ENTRY, oe);
        try {
            response = SocketClient.retrieveOnRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
         if (response != null) {
            String error = (String) response.getParams().get(UtilityConstants.ERROR);
            if (error != null) {
                System.out.println(SocketClientController.class.getSimpleName() + ": Error during new order creation: " + error);
             }
         }
     }
}
