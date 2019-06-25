package Welcome;

import Network.Network;
import Network.ForServer;
import home.JPotifyGUI;
import Network.User;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class SignUpGUI extends GoToJPotiy {

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
        signUpRequest = new ForServer(0,user);
        network = new Network(signUpRequest);
        new Thread(network).start();
        setVisible(false);
    }

    public Network getNetwork() {
        return network;
    }
}
