package other;

import home.JPotifyGUI;
import other.Song;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplaySongs extends JScrollPane {

    private ArrayList<JPanel> songPanels = new ArrayList<JPanel>();
    private JPanel panel;

    public DisplaySongs(ArrayList<Song> songArrayList){
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        setViewportView(panel);
        setBackground(Color.WHITE);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(layout);
        int counter = 0;
        for(Song s : songArrayList){
            JButton btn = new JButton();
            ImageIcon imageIcon = new ImageIcon(s.getArtwork2());
            btn.setIcon(imageIcon);
            btn.setSize(new Dimension(20 , 20));
            btn.setBackground(Color.WHITE);
            JLabel lbl = new JLabel(s.getMusicName() + "   " + s.getArtist() + "   " + s.getAlbumnane());
            lbl.setBackground(Color.white);
            lbl.setPreferredSize(new Dimension(20 , 5));
            JPanel pnl = new JPanel();
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            innerGbc.fill = GridBagConstraints.VERTICAL;
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(20 , 25));
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            pnl.add(btn);
            innerGbc.gridx = 0;
            innerGbc.gridy = 1;
            pnl.add(lbl);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            panel.add(pnl , gbc);
            songPanels.add(pnl);
            counter++;
        }
    }
}
