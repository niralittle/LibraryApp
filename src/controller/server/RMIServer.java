package controller.server;

import controller.LibraryService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class RMIServer  {
    public static void main(String[] args) throws RemoteException, NamingException {
        LibraryService service = new DBController();
        Context namingContext = new InitialContext();
        namingContext.rebind("rmi:books", service);
        System.out.println("RMI-server is working...");
    }
}
