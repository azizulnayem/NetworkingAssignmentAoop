public class ReadThreadClient extends Thread
{
    Client client;
    public ReadThreadClient(Client client)
    {
        this.client = client;
    }
    @Override
    public void run()
    {
        while (true)
        {
            Message text;
            try
            {
                text = (Message) client.networkUtil.read();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

            if(text.getFrom().equals("server"))
            {
                String[] messages = text.getText().split("~");
                for(String msg : messages)
                {
                    System.out.println(msg);
                }
            }
            else if(text.getFrom().equals("error"))
            {
                System.out.println("Wrong client name!");
            }
            else
            {
                System.out.println("From : "+text.getFrom() + " Message : " + text.getText());
            }
        }
    }
}