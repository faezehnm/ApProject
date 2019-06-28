package Network;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread implements Serializable {

    private ServerSocket serverSocket;
   // static ArrayList<Socket> clients = new ArrayList<>();


    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
     //   System.out.println(serverSocket.toString());
    }

    public void run()
    {
        while (true)
        {
            Socket client = null;
            try
            {
                client = serverSocket.accept();
                System.out.println("new client accepted!");

                //clients.add(client);

                ClientHandler clientHandler = new ClientHandler(client);
                //System.out.println(serverSocket.isClosed() + " " + serverSocket.toString());
                clientHandler.start();

            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        int port = 2000;
        try
        {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

