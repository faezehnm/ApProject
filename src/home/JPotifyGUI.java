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
import java.net.URL;
import java.util.Optional;

//import static Welcome.SignUpGUI.jPotifyGUI;

public class JPotifyGUI extends JFrame {
    private static final int WIDTH = 1850 , HEIGHT = 700;


    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    Song song = new Song("src/songs/Happier.mp3");

    private User user = new User("null","null") ;
    private PlayMusicGUI playMusicGUI = new PlayMusicGUI(this);
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
        getContentPane().setBackground(Color.pink);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(displayListsGUI);
        add(displayListsGUI , BorderLayout.WEST);

        add(topPanle,BorderLayout.PAGE_START);
        add(playMusicGUI,BorderLayout.PAGE_END);


        add(friendsActivityGUI,BorderLayout.EAST);
        setVisible(true);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                String fileName = "src/jpotify.bin";
                FileOutputStream filleout = null;
                try {
                    filleout = new FileOutputStream(fileName);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(filleout);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if(getPlayMusicGUI().getSong() == null){
                        out.writeInt(0);
                    }
                    else {
                        out.writeInt(1);
                        out.writeObject(getPlayMusicGUI().getSong());
                    }
                    out.writeInt(displayListsGUI.getDisplayListsControl().getSongs().size());
                    for (Song s : displayListsGUI.getDisplayListsControl().getSongs()){
                        out.writeObject(s);
                    }
                    out.writeInt(getDisplayListsGUI().getDisplayListsControl().getAlbumes().size());
                    for(Albume a : getDisplayListsGUI().getDisplayListsControl().getAlbumes()){
                        out.writeObject(a);
                    }
                    out.writeInt(getDisplayListsGUI().getDisplayListsControl().getPlaylists().size());
                    for(PlayList p : getDisplayListsGUI().getDisplayListsControl().getPlaylists()){
                        out.writeObject(p);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
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
