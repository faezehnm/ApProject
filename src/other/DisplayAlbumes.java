package other;

import javax.swing.*;
import java.awt.*;
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
            btn.setIcon(imageIcon);
            btn.setSize(new Dimension(200 , 200));
            btn.setBackground(Color.WHITE);
            JLabel lbl = new JLabel(a.getAlbumeName());
            lbl.setBackground(Color.white);
            lbl.setPreferredSize(new Dimension(200 , 50));
            JPanel pnl = new JPanel();
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            innerGbc.fill = GridBagConstraints.VERTICAL;
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(200 , 250));
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
}
