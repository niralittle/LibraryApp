package controller.client;

import shared.model.vo.Book;
import shared.model.vo.EntityWithId;
import shared.utils.PingPong;

import java.io.*;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 26.10.2014.
 */
public class ConnectionEstablisher {

    private static boolean isConnectionEstablished = false;

    private static final String HOST = "HOST";
    private static final int PORT = 12333;

    private static Socket socket;

    public static void connect() {
        if (isConnectionEstablished) {
            return;
        }
        int tryNumber = 1;
        while (!isConnectionEstablished) {
            if (++tryNumber > 25) {
                throw new RuntimeException("Something went badly wrong, you should call your parents.");
            }
            try {
                getConnection();
            } catch (Exception e) {
                System.out.println("Error while trying to establish connection: " + e.getCause() + " but trying again.");
            }
        }
    }

    public static void getConnection() throws IOException {
        socket = new Socket(HOST, PORT);
        System.out.println("Connection successfully established");
        isConnectionEstablished = true;
    }

    public static SecurityCheckOnClient.Result executeLoginAttempt(String username, String password)
            throws IOException {
        Map<String, Object> request = new HashMap<String, Object>();
        request.put("username", username);
        request.put("password", password);
        PingPong.ServerResponse response = retrieveOnRequest(request);
        return (SecurityCheckOnClient.Result) response.getParams().get("loginResult");

    }

    public static PingPong.ServerResponse retrieveOnRequest(Map<String, Object> request)
            throws IOException {
        connect();
        PingPong.ClientRequest clientRequest = new PingPong.ClientRequest();
        clientRequest.setParams(request);
        PingPong.ServerResponse serverResponse = null;

        ObjectOutputStream  oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(clientRequest);
        oos.close();

        SocketChannel sChannel = SocketChannel.open();
        sChannel.configureBlocking(true);
        if (sChannel.connect(socket.getLocalSocketAddress())) {
            ObjectInputStream ois =
                    new ObjectInputStream(sChannel.socket().getInputStream());
            try {
                serverResponse = (PingPong.ServerResponse) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Server response to request is: " + serverResponse.getParams());
        }
        return serverResponse;
    }
}
