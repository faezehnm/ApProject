import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayLists extends JPanel {

    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlists;
    private ArrayList<JButton> playlistsbtn;

    public DisplayLists(ArrayList<PlayList> playListArrayList) {
        setLayout(new GridLayout(10 , 1));
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlists = new JLabel("Playlists :");
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlists.setPreferredSize(new Dimension(200 , 50));
        add(songsbutton);
        add(albumebutton);
        add(playlists);
        if(playListArrayList.size() != 0){
            for(PlayList pl : playListArrayList){
                JButton plbtn = new JButton(pl.getPlayListName());
                plbtn.setBackground(Color.WHITE);
                plbtn.setPreferredSize(new Dimension(200 , 100));
                add(plbtn);
            }
        }
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlists.setBackground(Color.white);
    }
}
