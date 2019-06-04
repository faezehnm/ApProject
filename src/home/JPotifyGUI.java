package home;

import other.DisplayLists;
import playControl.PlayMusicGUI;

import javax.swing.*;
import java.awt.*;

public class JPotifyGUI extends JFrame {

    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    //Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/JPotify.jpg");

    private PlayMusicGUI playMusicGUI = new PlayMusicGUI();
    private DisplayLists displayLists = new DisplayLists(this);

    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(playMusicGUI,BorderLayout.PAGE_END);
        add(displayLists , BorderLayout.WEST);
        setVisible(true);
    }

}
