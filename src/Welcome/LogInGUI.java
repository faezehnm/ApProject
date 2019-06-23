package Welcome;

import Network.Network;
import Network.ForServer;
import home.JPotifyGUI;
import Network.User;

public class LogInGUI extends GoToJPotiy {
    private Network network ;
    private ForServer loginRequest ;
    private ForServer fromServer;
    public static JPotifyGUI jPotifyGUI;


    public LogInGUI() {
        super();
    }

    @Override
    public void actionToDoForEeach(User user) throws Exception {
        loginRequest = new ForServer(3,user);
        network = new Network(loginRequest);

        fromServer = (ForServer) network.getInputStream().readObject();
        if( fromServer.getType()==4 ){
            /*
            show a window with the message that pass is inCorrect
             */
        }
        if( fromServer.getType()==5 ){
            GO();
        }

    }

    private void GO() throws Exception {
        jPotifyGUI = new JPotifyGUI();
        this.setVisible(false);
    }
}
