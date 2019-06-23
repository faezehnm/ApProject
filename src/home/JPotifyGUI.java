package home;

import FirendsActivity.Friend;
import FirendsActivity.FriendsActivityGUI;
import FirendsActivity.Network;
import display.DisplayListsGUI;
import FirendsActivity.Network;
import display.DisplayListsGUI;
import music.Song;
import playControl.PlayMusicGUI;
import javax.swing.*;
import java.awt.*;

public class JPotifyGUI extends JFrame {
    private static final int WIDTH = 1850 , HEIGHT = 700;

    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    Song song = new Song("src/songs/Happier.mp3");

    private PlayMusicGUI playMusicGUI = new PlayMusicGUI();
    private DisplayListsGUI displayListsGUI = new DisplayListsGUI(this , playMusicGUI);
    private FriendsActivityGUI friendsActivityGUI = new FriendsActivityGUI(this);
    Friend friend = new Friend("198.0.0.1","faezeh");
    Friend friend2 = new Friend("198.0.0.1","amirreza");


    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(WIDTH , HEIGHT));
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        add(playMusicGUI,BorderLayout.PAGE_END);

        getContentPane().add(displayListsGUI);
        add(displayListsGUI , BorderLayout.WEST);

        friend.setLastSong(song);
        friend2.setLastSong(song);

        Friend.addFriend(friend);
        Friend.addFriend(friend2);


        friendsActivityGUI.creatFirendPanel();

        /*
        Network network = new Network(friendsActivityGUI,)
        */

        add(friendsActivityGUI,BorderLayout.EAST);
        setVisible(true);
    }

}
