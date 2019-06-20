package other;

import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DisplayAlbumes extends JScrollPane {

    private JPanel panel;
    private ArrayList<JPanel> albumPanels = new ArrayList<JPanel>();
    private DisplayListsControl displayListsControl;
    private PlayMusicGUI playMusicGUI;

    public DisplayAlbumes(ArrayList<Albume> albumes , DisplayListsControl displayListsContro , PlayMusicGUI playMusicGUIl){
        this.displayListsControl = displayListsControl;
        this.playMusicGUI = playMusicGUIl;
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
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        int counter = 0;
        for(Albume a : albumes){
            JButton btn = new JButton();
            ImageIcon imageIcon = new ImageIcon(a.getArtwork());
            Image resizedImg = (Image) scaledImage(imageIcon.getImage() , 370 , 370);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
            btn.setSize(new Dimension(370 , 370));
            btn.setBackground(Color.WHITE);
            btn.setBorder(greenLIne);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayListsControl.setDisplaySongs(a.getSongs() , playMusicGUI);
                }
            });
            JLabel lbl = new JLabel(a.getAlbumeName());
            lbl.setBackground(Color.white);
            lbl.setPreferredSize(new Dimension(370 , 50));
            lbl.setBorder(greenLIne);
            JPanel pnl = new JPanel();
            pnl.setBorder(greenLIne);
            pnl.setBackground(Color.WHITE);
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            innerGbc.fill = GridBagConstraints.VERTICAL;
            pnl.setSize(new Dimension(370 , 420));
            pnl.setLayout(innerLayout);
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            pnl.add(btn , innerGbc);
            innerGbc.gridx = 0;
            innerGbc.gridy = 1;
            pnl.add(lbl , innerGbc);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            panel.add(pnl , gbc);
            albumPanels.add(pnl);
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
