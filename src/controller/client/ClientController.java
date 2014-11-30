package controller.client;

import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by niralittle on 23.11.2014.
 */
public abstract class ClientController {

    private static ClientController controller;
    static {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("Client.properties")) {
            prop.load(input);
            switch (prop.getProperty("controller", "")) {
                case "rmi": controller = new RMIClientController();
                    break;
                case "socket": controller = new SocketClientController();
                    break;
                default: controller = new DBController();
                    break;
            }
        } catch (IOException io) {
            System.err.println("Exception during properties read: " + io.getCause());
        }
    }

    public static ClientController getInstance() {
        return controller;
    }

    public abstract User authorize(String login, String password);
    public abstract List<Book> getBookCatalogData();
    public abstract List<OrderEntry> getOEData();
    public abstract void createNewOrder(OrderEntry oe);

}
