package home;

import FirendsActivity.Friend;
import FirendsActivity.FriendsActivityGUI;
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

import static Welcome.SignUpGUI.jPotifyGUI;

public class JPotifyGUI extends JFrame {
    private static final int WIDTH = 1850 , HEIGHT = 700;

    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    Song song = new Song("src/songs/Happier.mp3");

    private PlayMusicGUI playMusicGUI = new PlayMusicGUI();
    private DisplayListsGUI displayListsGUI;
    private FriendsActivityGUI friendsActivityGUI = new FriendsActivityGUI(this);
    private TopPanle topPanle = new TopPanle();
    Friend friend = new Friend("198.0.0.1","faezeh");
    Friend friend2 = new Friend("198.0.0.1","amirreza");


    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(new Dimension(WIDTH , HEIGHT));
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        File file = new File("src/jpotify.bin");
        if(file.exists()){
            displayListsGUI = new DisplayListsGUI(this , playMusicGUI , false);
        }
        else {
            displayListsGUI = new DisplayListsGUI(this, playMusicGUI, true);
        }
        getContentPane().add(displayListsGUI);
        add(displayListsGUI , BorderLayout.WEST);

        add(topPanle,BorderLayout.PAGE_START);
        add(playMusicGUI,BorderLayout.PAGE_END);

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

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                //System.out.println("1");
                String fileName = "src/jpotify.bin";
                FileOutputStream filleout = null;
                try {
                    filleout = new FileOutputStream(fileName);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                //System.out.println(2);
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(filleout);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
               // System.out.println(3);
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
                   // System.out.println(getDisplayListsGUI().getDisplayListsControl().getPlaylists().size());
                    out.writeInt(getDisplayListsGUI().getDisplayListsControl().getPlaylists().size());
                    for(PlayList p : getDisplayListsGUI().getDisplayListsControl().getPlaylists()){
                        out.writeObject(p);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
               // System.out.println(4);
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
               // System.out.println("yessss");
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

}
