package other;

import other.Song;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplaySongs extends JScrollPane {

    private ArrayList<JButton> songbtns;
    private JPanel panel;

    public DisplaySongs(ArrayList<Song> songArrayList){
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        songbtns = new ArrayList<JButton>();
        setViewportView(panel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridLayout gl = new GridLayout(songArrayList.size() , 1);
        panel.setLayout(gl);
        for(Song s : songArrayList){
            JButton btn = new JButton();
            btn.setText(s.getMusicName());
            btn.setSize(new Dimension(500 , 100));
            btn.setBackground(Color.white);
            panel.add(btn);
            songbtns.add(btn);
        }
    }
}
