package Network;

import java.io.*;
import java.net.Socket;

public class Network implements Runnable {

    private Socket client;
    private int port = 2000 ;
    private String serverName = "localhost";
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;

    public Network(ForServer forServer) throws IOException
    {

        client = new Socket(serverName, port);
        outStream = new ObjectOutputStream(client.getOutputStream());
        outStream.writeObject(forServer);
//
//        OutputStream outputStream = client.getOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

    }

    public void sendFile(ForServer forServer) throws IOException
    {
        outStream.writeObject(forServer);

    }

    public ObjectInputStream getInputStream()
    {
        return inputStream;
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
                        break;
                    case 2:
                       /*
                       can add this user
                        */
                        break;
                    case 3:
                        /*
                        login request
                         */
                    case 4 :
                        /*
                        incorrect pass
                         */
                        break;
                    case 5:
                        /*
                        correct pass
                         */
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
