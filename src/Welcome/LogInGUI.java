package Welcome;

import Network.Network;
import Network.ForServer;
import home.JPotifyGUI;
import Network.User;

import java.io.Serializable;
/**
 * Log in GUI
 * @author faezeh naeimi
 * @version 1.0
 * @since 2019
 */
public class LogInGUI extends GoToJPotiy implements Serializable {
    private Network network ;
    private ForServer loginRequest ;
    private ForServer fromServer;

    /**
     * creat LogInGUI
     */
    public LogInGUI() {
        super();
    }

    /**
     * override action for Login's button
     * @param user user who wants to login
     * @throws Exception
     */
    @Override
    public void actionToDoForEeach(User user) throws Exception
    {
        loginRequest = new ForServer(3,user);
        network = new Network(loginRequest);
        new Thread(network).start();
        setVisible(false);
    }


}
