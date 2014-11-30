package controller.client;

import shared.model.dao.impl.BookDAOImpl;
import shared.model.dao.impl.OrderEntryDAOImpl;
import shared.model.dao.impl.UserDAOImpl;
import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import java.util.List;

public class DBController extends ClientController {

    @Override
    public User authorize(String login, String password) {
        User user = new UserDAOImpl().getUserByLogin(login);
        if (user!=null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<Book> getBookCatalogData() {
        return new BookDAOImpl().getByQuery(null);
    }

    @Override
    public List<OrderEntry> getOEData() {
        return new OrderEntryDAOImpl().getByQuery(null);
    }

    @Override
    public void createNewOrder(OrderEntry oe) {
        new OrderEntryDAOImpl().createOrder(oe);
    }
}
