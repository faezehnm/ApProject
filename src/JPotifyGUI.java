import javax.swing.*;
import java.awt.*;

public class JPotifyGUI extends JFrame {
    Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\naimi\\Desktop\\Icons\\musical-notes-symbols.png");

    PlayMusicBar playMusicBar = new PlayMusicBar();
    public JPotifyGUI(){
        super();
        setTitle("JPotify");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(icon);
        add(playMusicBar,BorderLayout.PAGE_END);
        setVisible(true);
    }
}
