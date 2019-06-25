package Welcome;

import Network.Network;
import Network.ForServer;
import home.JPotifyGUI;
import Network.User;

import java.io.Serializable;

public class LogInGUI extends GoToJPotiy implements Serializable {
    private Network network ;
    private ForServer loginRequest ;
    private ForServer fromServer;

    public LogInGUI() {
        super();
    }

    @Override
    public void actionToDoForEeach(User user) throws Exception {
        loginRequest = new ForServer(3,user);
        network = new Network(loginRequest);
        new Thread(network).start();
        setVisible(false);
    }

    public Network getNetwork() {
        return network;
    }

}
