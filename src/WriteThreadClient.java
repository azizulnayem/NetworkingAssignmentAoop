import java.io.IOException;
import java.util.Scanner;

class WriteThreadClient extends Thread
{
    Client client;
    public WriteThreadClient(Client client)
    {
        this.client = client;
    }
    @Override
    public void run()
    {
        System.out.println("1. send message to a person eg.(name,msg)\n2. get inbox history eg.(server,inbox)");
        while (true)
        {
            Scanner sc = new Scanner(System.in);
            String[] msg = sc.nextLine().split(",");
            try
            {
                client.networkUtil.write(new Message(client.clientName, msg[0], msg[1]));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}