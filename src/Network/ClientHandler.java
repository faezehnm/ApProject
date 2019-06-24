package Network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static Network.UserState.ME;

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


        ForServer fromClient = (ForServer)inputStream.readObject();
        user =  fromClient.getUser();


        if( fromClient.getType()== 0)
            checkUserName(user);

        if( fromClient.getType()== 3)
            checkPass(user);

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

    private void checkUserName (User user) throws IOException
    {

        boolean flag = false ;

        for( int i=0 ; i<users.size() ; i++ ){
            if( user.getName().equals(users.get(i).getName()) ){
                ForServer fromServer = new ForServer(1,user);
                outputStream.writeObject(fromServer);
                outputStream.flush();
                flag = true;
            }
        }

        if( !flag ) {
            ForServer fromServer = new ForServer(2,user);
            outputStream.writeObject(fromServer);
            outputStream.flush();
            users.add(user);
        }

    }

    private void checkPass(User user) throws IOException
    {
        boolean flag = false ;
        for(int i=0 ; i<users.size() ; i++ ){
            if( users.get(i).getPassword().equals(user.getPassword())){
                ForServer fromServer = new ForServer(5,user);
                outputStream.writeObject(fromServer);
                outputStream.flush();
                flag = true ;
            }
        }
        if( !flag ) {
            ForServer fromServer = new ForServer(4,user);
            outputStream.writeObject(fromServer);
            outputStream.flush();
        }
    }
}
