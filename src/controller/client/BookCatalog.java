package controller.client;

import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.view.desktop.MainWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 28.10.2014.
 */
public abstract class BookCatalog {

    public static final String GET_ALL_BOOKS = "getAllBooks";
    public static Order currentOrder;

    public static class Order {
        private List<Book> books;

        public Order() {
            books = new ArrayList<Book>();
        }

        public void addToOrder(List<Book> b) {
            if (currentOrder == null) {
                startNewOrder();
            }
            books.addAll(b);
        }

        public List<Book> getSelectedBooks() {
            return books;
        }
    }

    private static void sendOrderToProcessing() {
        OrderEntry oe = new OrderEntry();
        oe.setStatus(OrderEntry.OEStates.FRESH.getId());
        oe.setUserId(getCurrentUserIdFromMainWindow());
        oe.setBooks(currentOrder.getSelectedBooks());
    }

    public static List<Book> getAllBooks() throws IOException {
        Map<String, Object> request = new HashMap<String, Object>();
        request.put("method", GET_ALL_BOOKS);
        return (List<Book>) ConnectionEstablisher.retrieveOnRequest(request).getParams().get("methodResult");
    }

    public static void startNewOrder() {
        currentOrder = new Order();
    }

    public static void cancelOrder() {
        currentOrder.getSelectedBooks().clear();
    }

    private static int getCurrentUserIdFromMainWindow() {
        return MainWindow.getCurrentUserID();
    }

}
