package FirendsActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network implements Runnable{

    private Socket client;
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private FriendsActivityGUI friendsActivityGUI ;
    private Friend friend ;



    public Network(FriendsActivityGUI friendsActivityGUI, Friend friend) {
        this.friendsActivityGUI = friendsActivityGUI ;
        this.friend = friend ;
        String serverName = "localhost";
        int port = 2000;
        try
        {
            client = new Socket(serverName, port);

            outStream = new ObjectOutputStream(client.getOutputStream());

            outStream.writeObject(friend);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendFile(ForServer forServer) {
        try
        {
            outStream.writeObject(forServer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try
        {
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
                ForServer forServer = (ForServer) inputStream.readObject();

                switch (forServer.getType())
                {
                    case 0:
                        Friend.addFriend(forServer.getFriend());
                        friendsActivityGUI.creatFirendPanel();
                        friendsActivityGUI.addFriendsPanel();
                        break;
                    case 1:
                        /*
                        play Current/lastSong music for friends
                         */
                        break;
                    case 2:
                       /*
                       show shared Playlist for other friends
                        */
                        break;
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}
