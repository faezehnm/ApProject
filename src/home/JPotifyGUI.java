package home;

import FirendsActivity.Friend;
import FirendsActivity.FriendsActivityGUI;
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
        add(playMusicGUI,BorderLayout.PAGE_END);
        getContentPane().add(displayListsGUI);
        add(displayListsGUI , BorderLayout.WEST);
        friend.setLastSong(song);
        friend2.setLastSong(song);
        friend3.setLastSong(song);
        friend4.setLastSong(song);
        friend5.setLastSong(song);
        friend6.setLastSong(song);
        friend7.setLastSong(song);
        friend8.setLastSong(song);
        friend9.setLastSong(song);


       /* friendsActivityGUI.addFriend(friend);
        friendsActivityGUI.addFriend(friend2);
        friendsActivityGUI.addFriend(friend3);
        friendsActivityGUI.addFriend(friend4);
        friendsActivityGUI.addFriend(friend5);
        friendsActivityGUI.addFriend(friend6);
        friendsActivityGUI.addFriend(friend7);
        friendsActivityGUI.addFriend(friend8);
        friendsActivityGUI.addFriend(friend9);
        friendsActivityGUI.addFriend(friend5);
        friendsActivityGUI.addFriend(friend6);
        friendsActivityGUI.addFriend(friend7);
        friendsActivityGUI.addFriend(friend8);
        friendsActivityGUI.addFriend(friend9);*/

//        friendsActivityGUI.addFriend(friend5);
//        friendsActivityGUI.addFriend(friend6);
//        friendsActivityGUI.addFriend(friend7);
//        friendsActivityGUI.addFriend(friend8);
//        friendsActivityGUI.addFriend(friend9);
//        friendsActivityGUI.addFriend(friend2);
//        friendsActivityGUI.addFriend(friend3);
//        friendsActivityGUI.addFriend(friend4);
//        friendsActivityGUI.addFriend(friend5);
//        friendsActivityGUI.addFriend(friend6);
//        friendsActivityGUI.addFriend(friend7);
//        friendsActivityGUI.addFriend(friend8);
//        friendsActivityGUI.addFriend(friend9);
//        friendsActivityGUI.addFriend(friend5);
//        friendsActivityGUI.addFriend(friend6);
//        friendsActivityGUI.addFriend(friend7);
//        friendsActivityGUI.addFriend(friend8);
//        friendsActivityGUI.addFriend(friend9);
//        friendsActivityGUI.addFriend(friend5);
//        friendsActivityGUI.addFriend(friend6);
//        friendsActivityGUI.addFriend(friend7);
//        friendsActivityGUI.addFriend(friend8);
//        friendsActivityGUI.addFriend(friend9);
        friendsActivityGUI.creatFirendPanel();
        friendsActivityGUI.addFriendsPanel();
        /*
        Network network = new Network(friendsActivityGUI,)
        */

        add(friendsActivityGUI,BorderLayout.EAST);
        setVisible(true);
    }

}
