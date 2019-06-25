package home;

import FirendsActivity.Friend;
import FirendsActivity.FriendsActivityGUI;
import Network.Network;
import Network.User;
import TopPanle.TopPanle;
import display.DisplayListsGUI;
import music.Albume;
import music.PlayList;
import music.Song;
import playControl.PlayMusicGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Optional;

//import static Welcome.SignUpGUI.jPotifyGUI;

public class JPotifyGUI extends JFrame {
    private static final int WIDTH = 1850 , HEIGHT = 700;


    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    Song song = new Song("src/songs/Happier.mp3");

    private User user = new User("null","null") ;
    private PlayMusicGUI playMusicGUI = new PlayMusicGUI();
    private FriendsActivityGUI friendsActivityGUI = new FriendsActivityGUI(user);
    private DisplayListsGUI displayListsGUI = new DisplayListsGUI(this , playMusicGUI,false);
    private TopPanle topPanle = new TopPanle();

    Friend friend = new Friend("faezeh");
    Friend friend2 = new Friend("amirreza");

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        friendsActivityGUI.setUser(user);
        topPanle.setName(user.getName());
    }

    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(WIDTH , HEIGHT));
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        getContentPane().add(displayListsGUI);
        add(displayListsGUI , BorderLayout.WEST);

        add(topPanle,BorderLayout.PAGE_START);
        add(playMusicGUI,BorderLayout.PAGE_END);

        friend.setLastSong(song);
        friend2.setLastSong(song);


        friendsActivityGUI.creatFirendPanel();

        add(friendsActivityGUI,BorderLayout.EAST);
        setVisible(true);

    }

    public TopPanle getTopPanle() {
        return topPanle;
    }

    public PlayMusicGUI getPlayMusicGUI() {
        return playMusicGUI;
    }

    public DisplayListsGUI getDisplayListsGUI() {
        return displayListsGUI;
    }


    public FriendsActivityGUI getFriendsActivityGUI() {
        return friendsActivityGUI;
    }
}
