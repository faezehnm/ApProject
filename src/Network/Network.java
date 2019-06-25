package Network;

import FirendsActivity.Friend;
import Welcome.GoToJPotiy;
import Welcome.LogInGUI;
import Welcome.SignUpGUI;
import home.JPotifyGUI;
import music.Song;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Network implements Runnable{

    private Socket client;
    private int port = 2000 ;
    private String serverName = "localhost";
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private Warning warning ;
    public static JPotifyGUI jPotifyGUI ;

    public static LogInGUI logInGUI ;
    public static GoToJPotiy signUpGUI ;

    public Network(ForServer forServer) throws IOException
    {

        client = new Socket(serverName, port);
        outStream = new ObjectOutputStream(client.getOutputStream());
        outStream.writeObject(forServer);

    }

    public void sendFile(ForServer forServer) throws IOException
    {
        outStream.writeObject(forServer);
        outStream.flush();

    }

    @Override
    public void run() {
        try {
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
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
                       /*
                       send sign up request
                        */
                       break;
                    case 1:
                        NOTAcceptSignUpRequest();
                        break;

                    case 2:
                        AcceptSignUpRequest(forServer);
                        break;

                    case 3:
                        /*
                        login request
                         */
                    case 4 :
                        NOTAcceptLoginRequest();
                        break;

                    case 5:
                        AcceptLoginRequest(forServer);
                        break;

                    case 6:
                        Request request = new Request(forServer,jPotifyGUI);
                        /*
                        send regust to friend
                         */
                        break;

                    case 7:
                        System.out.println("friend accepted");
                        try {
                            freiendAccept(forServer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 8:
                       // friendNOTaccept(forServer);
                        break;

                    case 9:
                        /*
                        play Current/lastSong music for friends
                         */
                    case 10:
                        /*
                       show shared Playlist for other friends
                        */
                        break;
                    case 11:

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
    private void AcceptLoginRequest(ForServer forServer)
    {
        try {
            jPotifyGUI = new JPotifyGUI();
            jPotifyGUI.setUser(forServer.getUser());
            //jPotifyGUI.getFriendsActivityGUI().getAddFriendGUI().setNetwork(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void NOTAcceptLoginRequest()
    {
        logInGUI = new LogInGUI();
        warning= new Warning("incorrect pass");
    }

    private void AcceptSignUpRequest(ForServer forServer)
    {
        try {
            jPotifyGUI = new JPotifyGUI();
            jPotifyGUI.setUser(forServer.getUser());
            //jPotifyGUI.getFriendsActivityGUI().getAddFriendGUI().setNetwork(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void NOTAcceptSignUpRequest()
    {
        signUpGUI = new SignUpGUI();
        warning= new Warning("have this user already");
    }

    private void freiendAccept(ForServer forServer) throws Exception {
        Friend friend = new Friend(forServer.getUser().getName());
        /*
        set shared play list and song from forserver
         */
        friend.setLastSong(new Song("src/songs/Happier.mp3"));
        jPotifyGUI.getUser().addFriend(friend);
        jPotifyGUI.getFriendsActivityGUI().creatFirendPanel();

    }

//    private void friendNOTaccept(ForServer forServer)
//    {
//        warning= new Warning("user did not accept your request");
//    }

    private void request(ForServer forServer)
    {
        warning= new Warning(forServer.getUser().getPassword()+" request to follow you . if you accept press ok !");
    }
}

