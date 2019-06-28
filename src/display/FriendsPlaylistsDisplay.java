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

/**
 * This class is for displaying buutons of each friend's shared playlist on the center panel.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class FriendsPlaylistsDisplay extends JScrollPane {

    private User user;
    private JPanel panel;
    private ArrayList<JPanel> friendsPanels ;

    /**
     * Creates an object of this class
     * @param user is the user of this PC
     * @param displayListsControl is an Object of DisplayListsControl class that is controlling the left and center parts.
     */

    public FriendsPlaylistsDisplay(User user , DisplayListsControl displayListsControl){

        panel = new JPanel();
        friendsPanels = new ArrayList<JPanel>();
        setViewportView(panel);
        setBackground(Color.WHITE);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(layout);
        panel.setBackground(Color.BLACK);
        int counter = 0;
        System.out.println(user.getFriends().size());
        ArrayList<Friend> friends = user.getFriends();
        for(Friend friend : friends){
            System.out.println(friend.getName());
            PlayList playList = friend.getSharedPlayList();
            JPanel pnl = new JPanel();
            pnl.setBackground(Color.BLACK);
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
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayListsControl.setDisplaySongs(friend.getSharedPlayList().getSongs() , false);
                }
            });
            pnl.add(btn , innerGbc);
            JLabel lbl = new JLabel(friend.getName() + "'s shared playlist");
            lbl.setBackground(Color.BLACK);
            lbl.setPreferredSize(new Dimension(370, 50));
            lbl.setBorder(blueLIne);
            innerGbc.gridy = 1;
            innerGbc.gridx = 0;
            lbl.setForeground(Color.WHITE);
            pnl.add(lbl , innerGbc);
            friendsPanels.add(pnl);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            panel.add(pnl , gbc);
            counter++;
        }
    }
}
