package display;

import FirendsActivity.Friend;
import Network.User;
import music.PlayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FriendsPlaylistsDisplay extends JScrollPane {

    private User user;
    private JPanel panel;
    private ArrayList<JPanel> friendsPanels;

    public FriendsPlaylistsDisplay(User user , DisplayListsControl displayListsControl){
        this.user = user;
        panel = new JPanel();
        friendsPanels = new ArrayList<JPanel>();
        setViewportView(panel);
        setBackground(Color.WHITE);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(layout);
        panel.setBackground(Color.pink);
        int counter = 0;
        for(Friend friend : user.getFriends()){
            PlayList playList = friend.getSharedPlayList();
            JPanel pnl = new JPanel();
            pnl.setBackground(Color.CYAN);
            Border blueLIne = new LineBorder(Color.BLUE);
            pnl.setBorder(blueLIne);
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(370 , 420));
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            JButton btn = new JButton();
            Image resizedImg = playList.getSongs().get(0).scaledImage(370, 370);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
            btn.setBorder(blueLIne);
            btn.setForeground(Color.WHITE);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayListsControl.setDisplaySongs(friend.getSharedPlayList().getSongs() , false);
                }
            });
            pnl.add(btn , innerGbc);
            JLabel lbl = new JLabel(friend.getName() + "'s shared playlist");
            lbl.setBackground(Color.CYAN);
            lbl.setPreferredSize(new Dimension(370, 50));
            lbl.setBorder(blueLIne);
            innerGbc.gridy = 1;
            innerGbc.gridx = 0;
            lbl.setForeground(Color.WHITE);
            pnl.add(lbl , innerGbc);
            friendsPanels.add(pnl);
            panel.add(pnl , gbc);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            counter++;
        }
    }
}
