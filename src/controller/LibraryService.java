package controller;

import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import java.rmi.Remote;
import java.util.List;

public interface LibraryService extends Remote{

    User authorize(String login, String password);
    List<Book> getBookCatalogData();
    List<OrderEntry> getOEData();
    void createNewOrder(OrderEntry oe);
}
