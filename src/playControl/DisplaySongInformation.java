package playControl;

import music.Song;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplaySongInformation extends JPanel {
    private JLabel songNameLable = new JLabel();
    private JLabel albumNameLable = new JLabel();
    private JLabel artistNameLable = new JLabel();
    private Song song ;
    private DefaultListModel<String> artWorkList = new DefaultListModel<>();


    public DisplaySongInformation(Song song){

        this.song= song ;
        songNameLable.setText  ("song   : "+ song.getMusicName() +"\n");
        albumNameLable.setText ("album : "+ song.getAlbumeName() +"\n");
        artistNameLable.setText("artist  : "+ song.getArtist());
        artWorkList.addElement(songNameLable.getText());
        artWorkList.addElement(artistNameLable.getText());
        artWorkList.addElement(albumNameLable.getText());


        JList jList = new JList(artWorkList);
        add(jList);

    }
}
