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
        songbtns = new ArrayList<JButton>();
        setViewportView(panel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        panel.setLayout(new GridLayout(songArrayList.size() , 1));
        for(Song s : songArrayList){
            JButton btn = new JButton();
            btn.setText(s.getMusicName());
            btn.setPreferredSize(new Dimension(500 , 100));
            btn.setBackground(Color.white);
            panel.add(btn);
            songbtns.add(btn);
        }
    }
}
