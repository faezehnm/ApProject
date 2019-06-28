package Network;


import FirendsActivity.Friend;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import static Network.UserState.ME;

public class ClientHandler extends Thread{

    private Socket socket;
    private ObjectInputStream inputStream ;
    private ObjectOutputStream outputStream;
    private User user;
    static ArrayList<User> users = new ArrayList<User>();
    static HashMap<User,ObjectOutputStream> usersMap = new HashMap<User, ObjectOutputStream>();


    public ClientHandler(Socket client ) throws Exception
    {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;


            outputStream = new ObjectOutputStream(client.getOutputStream());
            inputStream = new ObjectInputStream(client.getInputStream());
            ForServer fromClient = (ForServer) inputStream.readObject();
            user =  fromClient.getUser();


            System.out.println(fromClient.getType());


            if( fromClient.getType() == 11)
                sendLastSongToFriends(user);

            else if( fromClient.getType()== 8)
                rejectFriend();

            else if( fromClient.getType()== 7)
                acceptFriend(user);

            else if( fromClient.getType()== 6)
                sendRequestToFriend(user);

            else if( fromClient.getType()== 0)
                checkUserName(user);

            else if( fromClient.getType()== 3)
                checkPass(user);



    }

    public void run ()
    {
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
            usersMap.put(user,outputStream);
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

    private void sendRequestToFriend(User user) throws IOException
    {
        ForServer fromServer = new ForServer(6,new User(user.getPassword(),null) );
        findUserSocket(user).writeObject(fromServer);
        findUserSocket(user).flush();
    }

    private ObjectOutputStream findUserSocket(User user)
    {

        ObjectOutputStream result = null ;
        for(User user1 : usersMap.keySet() ){
            if( user1.getName().equals(user.getName())){
                result = usersMap.get(user1);
                break;
            }
        }
        return result;
    }

    private void acceptFriend(User user) throws IOException
    {

        //User friend = new User(user.getPassword(),null);
        User friend = new User(user.getCurrentFriend().getName(),null);
        ForServer forServer = new ForServer(7,user);
        findUserSocket(friend).writeObject(forServer);
        findUserSocket(friend).flush();
    }

    private void rejectFriend()
    {
        /*
        :)
         */
    }

    private void sendLastSongToFriends(User user) throws IOException
    {
        for( int i=0 ; i<user.getFriends().size() ; i++ ){
            User hear = new User(user.getFriends().get(i).getName(),"null");
            ForServer forServer = new ForServer(12,user);
            findUserSocket(hear).writeObject(hear);
            findUserSocket(hear).flush();
        }
    }


}
