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
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

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
                System.out.println(SocketClient.class.getSimpleName() + ": Error while trying to establish connection but trying again.");
                //e.printStackTrace();
            }
        }
    }

    public static void getConnection() throws IOException {
        socket = new Socket(HOST, PORT);
        System.out.println(SocketClient.class.getSimpleName() + ": Connection successfully established");
        isConnectionEstablished = true;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

    }

    public static PingPong.ServerResponse retrieveOnRequest(PingPong.ClientRequest clientRequest)
            throws IOException {
        PingPong.ServerResponse serverResponse = null;
        if (socket == null) connect();
        oos.writeObject(clientRequest);

        try {
            serverResponse = (PingPong.ServerResponse) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(SocketClient.class.getSimpleName() + ": Server response to request is: " + serverResponse.getParams());
        isConnectionEstablished = false;
        return serverResponse;
    }
}
