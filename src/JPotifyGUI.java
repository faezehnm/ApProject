import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JPotifyGUI extends JFrame {
    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    //Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/JPotify.jpg");

    private PlayMusicBar playMusicBar = new PlayMusicBar();
    private DisplayLists displayLists = new DisplayLists(new ArrayList<PlayList>());

    public JPotifyGUI() throws Exception {
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(playMusicBar,BorderLayout.PAGE_END);
        add(displayLists , BorderLayout.WEST);
        setVisible(true);
    }
}
