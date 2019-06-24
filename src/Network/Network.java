package Network;

import Welcome.GoToJPotiy;
import Welcome.LogInGUI;
import Welcome.SignUpGUI;
import home.JPotifyGUI;

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
                        /*
                        have this user already
                         */
                        signUpGUI = new SignUpGUI();
                        warning= new Warning("have this user already");
                        break;

                    case 2:
                       /*
                       can add this user
                        */
                        try {
                            jPotifyGUI = new JPotifyGUI();
                            jPotifyGUI.getTopPanle().setName(forServer.getUser().getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 3:
                        /*
                        login request
                         */
                    case 4 :
                        /*
                        incorrect pass
                         */
                        logInGUI = new LogInGUI();
                        warning= new Warning("incorrect pass");
                        break;

                    case 5:
                        /*
                        correct pass
                         */
                        try {
                            jPotifyGUI = new JPotifyGUI();
                            jPotifyGUI.getTopPanle().setName(forServer.getUser().getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 6:
                        /*
                        play Current/lastSong music for friends
                         */
                        break;
                    case 7:
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
