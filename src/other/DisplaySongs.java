package other;

import home.JPotifyGUI;
import other.Song;
import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DisplaySongs extends JScrollPane {

    private ArrayList<JPanel> songPanels = new ArrayList<JPanel>();
    private JPanel panel;
    private PlayMusicGUI playMusicGUI;

    public DisplaySongs(ArrayList<Song> songArrayList , PlayMusicGUI playMusicGUI , DisplaySongsSituation situation , PlayList playList){
        this.playMusicGUI = playMusicGUI;
        panel = new JPanel();
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
        for(Song s : songArrayList){
            JButton btn = new JButton();
            btn.setBorder(greenLIne);
            if(s.getArtwork2() != null) {
                ImageIcon imageIcon = new ImageIcon(s.getArtwork2());
                Image resizedImg = (Image) scaledImage(imageIcon.getImage(), 370, 370);
                ImageIcon resizedIcon = new ImageIcon(resizedImg);
                btn.setIcon(resizedIcon);
            }
            else{
                ImageIcon musicIcon = new ImageIcon("src/Icons/music icon.png");
                Image resizedImg = scaledImage(musicIcon.getImage() , 370 , 370);
                ImageIcon resizedIcon = new ImageIcon(resizedImg);
                btn.setIcon(resizedIcon);
            }
            btn.setSize(new Dimension(370 , 370));
            btn.setBackground(Color.WHITE);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(situation.equals(DisplaySongsSituation.PLAYING)) {
                        playMusicGUI.setSong(s);
                    }
                    else{
                        playList.addSong(s);
                    }
                }
            });
            JLabel lbl = new JLabel(s.getMusicName() + "   " + s.getArtist() + "   " + s.getAlbumnane());
            lbl.setBorder(greenLIne);
            lbl.setBackground(Color.white);
            lbl.setPreferredSize(new Dimension(370 , 50));
            JPanel pnl = new JPanel();
            pnl.setBorder(greenLIne);
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(370 , 420));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            innerGbc.gridy = 0;
            innerGbc.gridx = 0;
            pnl.add(btn , innerGbc);
            innerGbc.gridx = 0;
            innerGbc.gridy = 1;
            pnl.add(lbl , innerGbc);
            pnl.setBackground(Color.white);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            panel.add(pnl , gbc);
            songPanels.add(pnl);
            counter++;
        }
    }

    private Image scaledImage(Image img , int width , int height){
        BufferedImage resizedImage = new BufferedImage(width , height , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img , 0 , 0 , width , height , null);
        g2.dispose();
        return resizedImage;
    }
}
