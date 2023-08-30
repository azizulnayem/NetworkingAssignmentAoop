import util.NetworkUtil;
import java.util.ArrayList;
public class NetworkInformation
{
    NetworkUtil networkUtil;
    ArrayList<String> inbox = new ArrayList<String>();
    public NetworkInformation(NetworkUtil networkUtil)
    {
        this.networkUtil = networkUtil;
    }

    public void addToInbox(String person, String msg){
        inbox.add("FROM : " + person + "  MESSAGE : " + msg);
    }
}
