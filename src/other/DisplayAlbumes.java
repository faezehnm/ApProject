package other;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DisplayAlbumes extends JScrollPane {

    private JPanel panel;
    private ArrayList<JPanel> albumPanels = new ArrayList<JPanel>();

    public DisplayAlbumes(ArrayList<Albume> albumes){
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
        for(Albume a : albumes){
            JButton btn = new JButton();
            ImageIcon imageIcon = new ImageIcon(a.getArtwork());
            Image resizedImg = (Image) scaledImage(imageIcon.getImage() , 350 , 350);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
            btn.setSize(new Dimension(350 , 350));
            btn.setBackground(Color.WHITE);
            JLabel lbl = new JLabel(a.getAlbumeName());
            lbl.setBackground(Color.white);
            lbl.setPreferredSize(new Dimension(350 , 50));
            JPanel pnl = new JPanel();
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            innerGbc.fill = GridBagConstraints.VERTICAL;
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(350 , 400));
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            pnl.add(btn);
            innerGbc.gridx = 0;
            innerGbc.gridy = 1;
            pnl.add(lbl);
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
