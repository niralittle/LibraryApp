package controller.server;

import shared.utils.PingPong;
import shared.utils.UtilityConstants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by niralittle on 23.11.2014.
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java SocketServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
        ) {

            PingPong.ClientRequest request;
            PingPong.ServerResponse response;
            System.out.println("Server listening on port: " + portNumber);
            while ((request = (PingPong.ClientRequest) inFromClient.readObject()) != null) {
                System.out.println("Received a request from client.");
                if (request.getParams().get(UtilityConstants.BREAK_CONNECTION) != null) {
                    break;
                }
                response = SocketServerController.processRequest(request);
                outToClient.writeObject(response);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
