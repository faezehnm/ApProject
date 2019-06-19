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
        panel.setBackground(Color.RED);
        songbtns = new ArrayList<JButton>();
        setViewportView(panel);
        setBackground(Color.BLACK);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridLayout gl = new GridLayout(songArrayList.size() , 1);
        panel.setLayout(gl);
        for(Song s : songArrayList){
            JButton btn = new JButton();
            btn.setText(s.getMusicName());
            ImageIcon imageIcon = new ImageIcon(s.getArtwork2());
            btn.setIcon(imageIcon);
            btn.setSize(new Dimension(500 , 100));
            btn.setBackground(Color.WHITE);
            panel.add(btn);
            songbtns.add(btn);
        }
        //System.out.println(songArrayList.size());
        //System.out.println(songbtns.size());
    }
}
