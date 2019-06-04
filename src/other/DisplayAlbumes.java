package other;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayAlbumes extends JScrollPane {

    private JPanel panel;
    private ArrayList<JButton> albumbtns;

    public DisplayAlbumes(ArrayList<Albume> albumes){
        panel = new JPanel();
        albumbtns = new ArrayList<JButton>();
        setViewportView(panel);
        setVisible(true);
        panel.setLayout(new GridLayout(albumes.size() , 1));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        for(Albume a : albumes){
            JButton btn = new JButton(a.getAlbumeName());
            btn.setBackground(Color.white);
            btn.setPreferredSize(new Dimension(500, 100));
            albumbtns.add(btn);
            panel.add(btn);
        }
    }
}
