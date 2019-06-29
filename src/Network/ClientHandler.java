package Network;


import FirendsActivity.Friend;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *  hanld clients of server
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class ClientHandler extends Thread{

    private Socket socket;
    private ObjectInputStream inputStream ;
    private ObjectOutputStream outputStream;
    private User user;
    static ArrayList<User> users = new ArrayList<User>();
    static HashMap<User,ObjectOutputStream> usersMap = new HashMap<User, ObjectOutputStream>();

    /**
     * creat client handler
     * @param client client which connected to server
     * @throws Exception
     */
    public ClientHandler(Socket client ) throws Exception
    {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;


            outputStream = new ObjectOutputStream(client.getOutputStream());
            inputStream = new ObjectInputStream(client.getInputStream());
            ForServer fromClient = (ForServer) inputStream.readObject();
            user =  fromClient.getUser();


            System.out.println(fromClient.getType());

            if( fromClient.getType() == 13 )
                sendNewSongForFriend(user);

            else if( fromClient.getType() == 11)
                sendLastSongToFriends(user);

            else if( fromClient.getType()== 7)
                acceptFriend(user);

            else if( fromClient.getType()== 6)
                sendRequestToFriend(user);

            else if( fromClient.getType()== 0)
                checkUserName(user);

            else if( fromClient.getType()== 3)
                checkPass(user);



    }

    private void sendNewSongForFriend(User user) throws IOException
    {

        for( int i=0 ; i<user.getFriends().size() ; i++ ){
            User hear = new User(user.getFriends().get(i).getName(),user.getName());
            ForServer forServer = new ForServer(14,user);
            findUserSocket(hear).writeObject(forServer);
            findUserSocket(hear).flush();
        }

    }

    /**
     * check if we have already a user with this userName
     * @param user client's userName
     * @throws IOException
     */
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

    /**
     * check passWord of user
     * @param user client's userName
     * @throws IOException
     */
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

    /**
     * send request to friend
     * @param user client's userName
     * @throws IOException
     */
    private void sendRequestToFriend(User user) throws IOException
    {
        ForServer fromServer = new ForServer(6,new User(user.getPassword(),null) );
        findUserSocket(user).writeObject(fromServer);
        findUserSocket(user).flush();
    }

    /**
     * find user socket with her name
     * @param user client's userName
     * @return ObjectOutputStream of user's socket
     */
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

    /**
     * when a friend accpeted request of other friend
     * @param user client's userName( who accepted request )
     * @throws IOException
     */
    private void acceptFriend(User user) throws IOException
    {

        //User friend = new User(user.getPassword(),null);
        User friend = new User(user.getCurrentFriend().getName(),null);
        ForServer forServer = new ForServer(7,user);
        findUserSocket(friend).writeObject(forServer);
        findUserSocket(friend).flush();
    }

    /**
     * when a user listen to song which exist in her sharedPlaylist , send it for all her friends.
     * @param user
     * @throws IOException
     */
    private void sendLastSongToFriends(User user) throws IOException
    {
        for( int i=0 ; i<user.getFolowers().size() ; i++ ){
            //User hear = new User(user.getFriends().get(i).getName(),user.getName());
            User hear = new User(user.getFolowers().get(i).getName(),user.getName());
            ForServer forServer = new ForServer(12,user);
            findUserSocket(hear).writeObject(forServer);
            findUserSocket(hear).flush();
        }
    }

    public void run () {}

}
