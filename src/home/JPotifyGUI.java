package home;

import FirendsActivity.Friend;
import FirendsActivity.FriendsActivityGUI;
import FirendsActivity.Network;
import other.DisplayListsGUI;
import FirendsActivity.Network;
import other.DisplayListsGUI;
import other.Song;
import playControl.PlayMusicGUI;
import javax.swing.*;
import java.awt.*;

public class JPotifyGUI extends JFrame {

    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    private static final int WIDTH = 1850 , HEIGHT = 700;

    private PlayMusicGUI playMusicGUI = new PlayMusicGUI();
    private DisplayListsGUI displayListsGUI = new DisplayListsGUI(this);
    private FriendsActivityGUI friendsActivityGUI = new FriendsActivityGUI(this);
    Friend friend = new Friend("198.0.0.1","faezeh");
    Friend friend2 = new Friend("198.0.0.1","amirreza");
    Friend friend3 = new Friend("198.0.0.1","amirreza");
    Friend friend4 = new Friend("198.0.0.1","amirreza");
    Friend friend5 = new Friend("198.0.0.1","amirreza");
    Friend friend6 = new Friend("198.0.0.1","amirreza");
    Friend friend7 = new Friend("198.0.0.1","amirreza");
    Friend friend8 = new Friend("198.0.0.1","amirreza");
    Friend friend9 = new Friend("198.0.0.1","amirreza");

    Song song = new Song("src/songs/Happier.mp3");

    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(WIDTH , HEIGHT));
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playMusicGUI.setSong(song);
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
