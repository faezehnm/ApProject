package Welcome;

import home.JPotifyGUI;
import home.User;

public class SignUpGUI extends GoToJPotiy {
    public static JPotifyGUI jPotifyGUI ;

    @Override
    public void actionToDoForEeach(User user) throws Exception {
        User.addUser(user);
        /*
        action for add user
         */
        jPotifyGUI = new JPotifyGUI();
        setVisible(false);

    }
}
