package Welcome;

import home.JPotifyGUI;
import home.User;

public class LogInGUI extends GoToJPotiy {
    public static JPotifyGUI jPotifyGUI;

    public LogInGUI() {
        super();
    }

    public void actionToDoForEeach(User user) throws Exception {
        /*
        actiod to sign up
         */
        jPotifyGUI = new JPotifyGUI();
        this.setVisible(false);
    }
}
