package controller.client;
import shared.utils.PingPong;

import java.io.*;
import java.net.Socket;

/**
 * Created by niralittle on 26.10.2014.
 */
public class SocketClient {

    private static boolean isConnectionEstablished = false;

    private static final String HOST = "localhost";
    private static final int PORT = 12350;

    private static Socket socket;

    public static void connect() {
        if (isConnectionEstablished) {
            return;
        }
        int tryNumber = 1;
        while (!isConnectionEstablished) {
            if (++tryNumber > 25) {
                throw new RuntimeException("Something went badly wrong.");
            }
            try {
                getConnection();
            } catch (Exception e) {
                System.out.println("Error while trying to establish connection but trying again.");
                e.printStackTrace();
            }
        }
    }

    public static void getConnection() throws IOException {
        socket = new Socket(HOST, PORT);
        System.out.println("Connection successfully established");
        isConnectionEstablished = true;
    }

    public static PingPong.ServerResponse retrieveOnRequest(PingPong.ClientRequest clientRequest)
            throws IOException {
        connect();
        PingPong.ServerResponse serverResponse = null;

        ObjectOutputStream  oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(clientRequest);

        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            serverResponse = (PingPong.ServerResponse) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Server response to request is: " + serverResponse.getParams());
         return serverResponse;
    }
}
