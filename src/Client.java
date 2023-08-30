import util.NetworkUtil;
import java.util.Scanner;


public class Client {
    NetworkUtil networkUtil;
    String clientName;

    public Client(String serverAddress, int serverPort) {
        try {
            System.out.print("Enter name of the client: ");
            Scanner sc = new Scanner(System.in);
            clientName = sc.nextLine();
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);
            System.out.println("Connected to server...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort);
        ReadThreadClient readThreadClient = new ReadThreadClient(client);
        readThreadClient.start();
        WriteThreadClient writeThreadClient=new WriteThreadClient(client);
        writeThreadClient.start();
    }
}


