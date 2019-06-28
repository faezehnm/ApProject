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

/**
 * Creates the main frame of JPotify and serializes the information when closing.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh & Faezeh Naeimi
 * @since 2019
 * @version 1.0
 */


public class JPotifyGUI extends JFrame {

    private static final int WIDTH = 1850 , HEIGHT = 700;
    private Image icon;
    private User user;
    private PlayMusicGUI playMusicGUI;
    private FriendsActivityGUI friendsActivityGUI;
    private DisplayListsGUI displayListsGUI;
    private TopPanle topPanle;

    /**
     * Crates an oject of this class
     * @param setPlaylists
     */

    public JPotifyGUI(boolean setPlaylists) {
        super();

        icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
        user = new User("null","null") ;
        try {
            playMusicGUI  = new PlayMusicGUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        friendsActivityGUI = new FriendsActivityGUI(user);

        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(WIDTH , HEIGHT));
        setIconImage(icon);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(setPlaylists){
           displayListsGUI  = new DisplayListsGUI(this , playMusicGUI,true , user);
        }
        else{
            displayListsGUI = new DisplayListsGUI(this , playMusicGUI,false , user);
        }
        topPanle =  new TopPanle(displayListsGUI.getDisplayListsControl());
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
                String fileName = "src/" + user.getName() + ".bin";
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

    public void setUser(User user) {
        this.user = user;
        friendsActivityGUI.setUser(user);
        topPanle.setName(user.getName());
    }

    public User getUser() {
        return user;
    }

}
