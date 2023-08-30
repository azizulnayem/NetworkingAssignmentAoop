import util.NetworkUtil;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    HashMap<String, NetworkInformation> clientNetworkInformationMap = new HashMap<String, NetworkInformation>();

    Server() {
        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server has started. Waiting for connections...");
            Scanner sc = new Scanner(System.in);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server has accepted a connection...");
                serve(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkInformation networkInformation = new NetworkInformation(new NetworkUtil(clientSocket));
        String client = (String) networkInformation.networkUtil.read();
        System.out.println(client + " joined.");
        clientNetworkInformationMap.put(client, networkInformation);
        ReadThreadServer readThreadServer = new ReadThreadServer(clientNetworkInformationMap, networkInformation);
        readThreadServer.start();
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
