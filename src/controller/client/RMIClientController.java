package controller.client;

import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import java.util.List;

/**
 * Created by niralittle on 23.11.2014.
 */
public class RMIClientController extends ClientController{
    @Override
    public User authorize(String login, String password) {
        return null;
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
