import java.util.ArrayList;
import java.util.HashMap;


public class ReadThreadServer extends Thread
{
    HashMap<String, NetworkInformation> clientNetworkInformationMap;
    NetworkInformation networkInformation;
    public ReadThreadServer(HashMap<String, NetworkInformation> clientNetworkInformationMap, NetworkInformation networkInformation)
    {
        this.clientNetworkInformationMap = clientNetworkInformationMap;
        this.networkInformation = networkInformation;
    }
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Message message = (Message) networkInformation.networkUtil.read();
                if(message.getTo().equals("server"))
                {
                    ArrayList<String> inbox = networkInformation.inbox;
                    StringBuilder inboxMsg = new StringBuilder();
                    for (String msg : inbox)
                    {
                        inboxMsg.append("~").append(msg);
                    }
                    networkInformation.networkUtil.write(new Message("server", "client", inboxMsg.toString()));
                }
                else if(clientNetworkInformationMap.get(message.getTo())==null)
                {
                    networkInformation.networkUtil.write(new Message("error","",""));
                }
                else
                {
                    NetworkInformation receiver = clientNetworkInformationMap.get(message.getTo());
                    receiver.addToInbox(message.getFrom(), message.getText());
                    receiver.networkUtil.write(message);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}