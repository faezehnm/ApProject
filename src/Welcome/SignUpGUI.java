package Welcome;

import Network.Network;
import Network.ForServer;
import home.JPotifyGUI;
import Network.User;

import java.io.IOException;
import java.io.Serializable;

public class SignUpGUI extends GoToJPotiy implements Serializable {

    public static JPotifyGUI jPotifyGUI ;
    private Network network;
    private ForServer signUpRequest ;
    private ForServer fromServer;

    public SignUpGUI()
    {
        super();
    }

    @Override
    public void actionToDoForEeach(User user) throws Exception
    {
        User.addUser(user);
        signUpRequest = new ForServer(0,user);
        network = new Network(signUpRequest);

        fromServer = (ForServer) network.getInputStream().readObject();
        if( fromServer.getType()==1 ){
            /*
            show a window with a message that this user has Already exist
             */
        }
        if( fromServer.getType()==2 ){
            GO();
        }

    }

    private void GO() throws Exception
    {
        jPotifyGUI = new JPotifyGUI();
        setVisible(false);
    }
}
