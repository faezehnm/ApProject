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
    static HashMap<User,OutputStream> usersMapTyp2 = new HashMap<User, OutputStream>();
    private OutputStream outputStreamTyp2 ;
    static boolean isOnNetworkType2 = false ;
    static ArrayList<Friend> friends = new ArrayList<Friend>();
    static String path ;

    public ClientHandler(Socket client ) throws Exception
    {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;

        if( !isOnNetworkType2 ) {

            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStreamTyp2 = client.getOutputStream();
            inputStream = new ObjectInputStream(client.getInputStream());
            ForServer fromClient = (ForServer) inputStream.readObject();
            user =  fromClient.getUser();

            System.out.print(fromClient.getType());
            System.out.println(isOnNetworkType2);

            if( fromClient.getType() == 13)
                updateUser(user);

            else if( fromClient.getType() == 11)
                sendLastSongToFriends(user);

            else if (fromClient.getType() == 9) {
                path = "src/songs/"+user.getName()+".mp3";
                actionForReceivefileFromClient(user);
                prepareFriendsToSendFile(user);
            }
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
        else if( isOnNetworkType2) {
            isOnNetworkType2 = false;
            receivefileFromClient(client);
            sendFileToFriends();

        }


    }

    public void run ()
    {

//        try
//        {
//            while (!socket.isClosed())
//            {
//                ForServer forServer = (ForServer)inputStream.readObject();
//                /*
//                do something for friend :)
//                 */
//            }
//
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }

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
            usersMapTyp2.put(user,outputStreamTyp2);
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

    private OutputStream findUserSocketType2(User user)
    {

        OutputStream result = null ;
        for(User user1 : usersMapTyp2.keySet() ){
            if( user1.getName().equals(user.getName())){
                result = usersMapTyp2.get(user1);
                break;
            }
        }
        return result;
    }

    private void acceptFriend(User user) throws IOException
    {

        User friend = new User(user.getPassword(),null);
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

    private void actionForReceivefileFromClient(User user)
    {
        isOnNetworkType2= true ;
        for(int i=0 ; i<user.getFriends().size() ; i++ ){
            friends.add(user.getFriends().get(i));
        }
    }

    private void prepareFriendsToSendFile(User user) throws IOException
    {
        for( int i=0 ; i<friends.size() ;i++ ){
            User hear = new User(friends.get(i).getName(),"null");
            ForServer forServer = new ForServer(10,new User(user.getName(),"null"));
            findUserSocket(hear).writeObject(forServer);
            findUserSocket(hear).flush();
        }

    }

    private void receivefileFromClient(Socket socket) throws IOException
    {
        InputStream in = null;
        OutputStream out = null;
        in = socket.getInputStream();
        out = new FileOutputStream(path);
        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
        out.close();
        in.close();

    }

    private void sendFileToFriends() throws IOException
    {
        for( int i=0 ; i<friends.size() ;i++){

            User hear = new User(friends.get(i).getName(),"null");
            OutputStream out = findUserSocketType2(hear);

            File file = new File(path);
            long length = file.length();
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.flush();
            out.close();
            in.close();
        }
        emptyFriends();
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

    private void emptyFriends()
    {
        for( int i=0 ; i<friends.size() ; i++ ){
            friends.remove(i);
        }
    }

    private void updateUser(User user)
    {
        for(User user1 : usersMap.keySet() ){
            if( user1.getName().equals(user.getName())){
                usersMap.remove(usersMap.get(user1));
                usersMap.put(user1,outputStream);
                break;
            }
        }
        for(User user1 : usersMapTyp2.keySet() ){
            if( user1.getName().equals(user.getName())){
                usersMapTyp2.remove(usersMapTyp2.get(user1));
                usersMapTyp2.put(user1,outputStreamTyp2);
                break;
            }
        }
        for( int i=0 ; i<users.size() ; i++ ){
            if( user.getName().equals(users.get(i).getName()) ){
                users.remove(i);
                users.add(user);
                break;
            }
        }

    }

}
