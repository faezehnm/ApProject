package display;

import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import music.*;

public abstract class DisplaySongsGroup extends JScrollPane {

    protected JPanel panel;
    protected ArrayList<? extends Music> musics;
    protected ArrayList<JPanel> musicsPanels;
    protected PlayMusicGUI playMusicGUI;

    public DisplaySongsGroup(ArrayList<? extends Music> musics , PlayMusicGUI playMusicGUI) throws Exception {
        panel = new JPanel();
        musicsPanels = new ArrayList<JPanel>();
        this.musics = musics;
        this.playMusicGUI = playMusicGUI;
        panel.setBackground(Color.WHITE);
        setViewportView(panel);
        setBackground(Color.WHITE);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(layout);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        int counter = 0;
        for(Music music : musics){
            JPanel pnl = new JPanel();
            pnl.setBackground(Color.WHITE);
            pnl.setBorder(greenLIne);
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(370 , 420));
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            JButton btn = createButton(music);
            pnl.add(btn , innerGbc);
            addActionListeners(btn , music);
            innerGbc.gridx = 0;
            innerGbc.gridy = 1;
            pnl.add(createLabel(music) , innerGbc);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            panel.add(pnl , gbc);
            musicsPanels.add(pnl);
            counter++;
        }
    }

    private JButton createButton(Music music){
        JButton btn = new JButton();
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        btn.setBorder(greenLIne);
        if(music.getArtwork() != null) {
            Image resizedImg = music.scaledImage(370, 370);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
        }
        else{
            Image resizedImg = music.scaledImage(370 , 370);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
        }
        btn.setSize(new Dimension(370 , 370));
        btn.setBackground(Color.WHITE);
        return btn;
    }

    private JLabel createLabel(Music music) {
        JLabel lbl = new JLabel();
        lbl.setBackground(Color.white);
        lbl.setPreferredSize(new Dimension(370, 50));
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        lbl.setBorder(greenLIne);
        lbl.setText(music.getName());
        if(music instanceof Song){
            lbl.setText(lbl.getText() + "   " + ((Song) music).getAlbumeName() + "   " + ((Song) music).getArtist());
        }
        return lbl;
    }

    protected abstract void addActionListeners(JButton btn , Music music) throws Exception;

    public ArrayList<? extends Music> getMusics() {
        return musics;
    }
}
