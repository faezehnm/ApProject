import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class JPotifyGUI extends JFrame {
    Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");

    PlayMusicBar playMusicBar;
    {
        try {
            playMusicBar = new PlayMusicBar();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public JPotifyGUI(){
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(playMusicBar,BorderLayout.PAGE_END);
        setVisible(true);
    }
}
