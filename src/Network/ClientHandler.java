package Network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{

    private Socket socket;
    private ObjectInputStream inputStream ;
    private ObjectOutputStream outputStream;
    private User user;
    static ArrayList<User> users = new ArrayList<User>();


    public ClientHandler(Socket client ) throws Exception
    {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;
        outputStream=  new ObjectOutputStream(client.getOutputStream());
        inputStream  = new ObjectInputStream(client.getInputStream());

//        if( true)
           //System.out.println(inputStream.readObject().getClass());
//        else
//            System.out.println(":)))");

        ForServer fromClient = (ForServer)inputStream.readObject();
        System.out.println(fromClient.getUser().getName());
        user =  fromClient.getUser();

        checkUserName(user);


    }

    public void run ()
    {

        try
        {
            while (!socket.isClosed())
            {
                ForServer forServer = (ForServer)inputStream.readObject();
                /*
                do something for friend :)
                 */
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private void checkUserName (User user) throws IOException {
        boolean flag = false ;
        for( int i=0 ; i<users.size() ; i++ ){
            if( user == users.get(i)){
                ForServer fromServer = new ForServer(1,user);
                outputStream.writeObject(fromServer);
                flag = true;
            }
        }
        if( !flag ) {
            ForServer fromServer = new ForServer(2,user);
            outputStream.writeObject(fromServer);
            users.add(user);
        }
    }
}
