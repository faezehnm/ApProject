package home;

import FirendsActivity.Friend;
import FirendsActivity.FriendPanel;
import FirendsActivity.FriendsActivityGUI;
import other.DisplayLists;
import other.Song;
import playControl.PlayMusicGUI;
import javax.swing.*;
import java.awt.*;

public class JPotifyGUI extends JFrame {

    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    //Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/JPotify.jpg");

    private PlayMusicGUI playMusicGUI = new PlayMusicGUI();
    private DisplayLists displayLists = new DisplayLists(this);
    private FriendsActivityGUI friendsActivityGUI = new FriendsActivityGUI(this);
    Friend friend = new Friend("198.0.0.1","faezeh");
    Friend friend2 = new Friend("198.0.0.1","amirreza");
    Friend friend3 = new Friend("198.0.0.1","amirreza");
    Friend friend4 = new Friend("198.0.0.1","amirreza");
    Friend friend5 = new Friend("198.0.0.1","amirreza");
    Friend friend6 = new Friend("198.0.0.1","amirreza");

    Song song = new Song("src/songs/Happier.mp3");

    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(playMusicGUI,BorderLayout.PAGE_END);
        add(displayLists , BorderLayout.WEST);

        friend.setLastSong(song);
        friend2.setLastSong(song);
        friend3.setLastSong(song);
        friend4.setLastSong(song);
        friend5.setLastSong(song);
        friend6.setLastSong(song);
//        friendPanel = new FriendPanel(friend);
//        add(friendPanel);
        friendsActivityGUI.addFriend(friend);
        friendsActivityGUI.addFriend(friend2);
        friendsActivityGUI.addFriend(friend3);
        friendsActivityGUI.addFriend(friend4);
        friendsActivityGUI.addFriend(friend5);
        friendsActivityGUI.addFriend(friend6);
        friendsActivityGUI.creatFirendPanel();
        friendsActivityGUI.addFriendsPanel();
        add(friendsActivityGUI,BorderLayout.EAST);
        setVisible(true);
    }

}
