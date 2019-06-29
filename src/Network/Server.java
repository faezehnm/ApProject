package Network;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *  server runnig on special port
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class Server extends Thread implements Serializable {

    private ServerSocket serverSocket;

    /**
     * creat a server
     * @param port port of server
     * @throws IOException
     */
    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
     //   System.out.println(serverSocket.toString());
    }

    /**
     * listen to clients f
     */
    public void run()
    {
        while (true)
        {
            Socket client = null;
            try
            {
                client = serverSocket.accept();
                System.out.println("new client accepted!");

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

    public static void main(String[] args)
    {
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

