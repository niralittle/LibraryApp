package controller.client;


import controller.server.DBController;
import controller.LibraryService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by niralittle on 23.11.2014.
 */
public abstract class ClientController implements LibraryService, Serializable {

    private static ClientController controller;
    static {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("Client.properties")) {
            prop.load(input);
            switch (prop.getProperty("controller", "")) {
                case "rmi": Context namingContext = new InitialContext();
                    controller = (ClientController) namingContext.lookup("rmi:books");
                    break;
                case "socket": controller = new SocketClientController();
                    break;
                default: controller = new DBController();
                    break;
            }
        } catch (IOException | NamingException e) {
            System.err.println("Exception during properties read: " + e.getCause());
        }
    }

    public static ClientController getInstance() {
        return controller;
    }


}
