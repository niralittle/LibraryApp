package controller.client;

import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import java.util.List;

/**
 * Created by niralittle on 23.11.2014.
 */
public abstract class ClientController {

    private static ClientController controller = new SocketClientController();
    //private static ClientController controller = new RMIClientController();

    public static ClientController getInstance() {
        return controller;
    }

    public abstract User authorize(String login, String password);
    public abstract List<Book> getBookCatalogData();
    public abstract List<OrderEntry> getOEData();
    public abstract void createNewOrder(OrderEntry oe);

}
