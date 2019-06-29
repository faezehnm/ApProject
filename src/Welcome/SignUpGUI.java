package Welcome;

import Network.Network;
import Network.ForServer;
import home.JPotifyGUI;
import Network.User;

import java.io.IOException;

import static java.lang.Thread.sleep;
/**
 * sign up GUI
 * @author faezeh naeimi
 * @version 1.0
 * @since 2019
 */
public class SignUpGUI extends GoToJPotiy {

    private Network network;
    private ForServer signUpRequest ;
    private ForServer fromServer;

    /**
     * creat SignUpGUI
     */
    public SignUpGUI()
    {
        super();
    }

    /**
     * override action for signUp's button
     * @param user user who wants to sign up
     * @throws Exception
     */
    @Override
    public void actionToDoForEeach(User user) throws Exception
    {
        signUpRequest = new ForServer(0,user);
        network = new Network(signUpRequest);
        new Thread(network).start();
        setVisible(false);
    }

}
