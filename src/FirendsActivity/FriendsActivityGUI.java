package FirendsActivity;

import home.JPotifyGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FriendsActivityGUI extends JScrollPane implements ActionListener {

    private JPanel mainPanel;
    private ArrayList<FriendPanel> friendsPanel = new ArrayList<FriendPanel>();

    public FriendsActivityGUI(JPotifyGUI gui)
    {

        mainPanel = new JPanel(new GridLayout(0,1));
        setViewportView(mainPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);

    }

    public void creatFirendPanel()
    {
        for( int i=0 ; i<Friend.getFriends().size() ; i++ ){
            friendsPanel.add(new FriendPanel(Friend.getFriends().get(i)));
        }
    }

    public void addFriendsPanel()
    {
        for( int i=0 ; i<friendsPanel.size() ; i++ ){
            mainPanel.add(friendsPanel.get(i).getMainPanel());
            Border blueline = BorderFactory.createLineBorder(Color.CYAN.brighter());
            friendsPanel.get(i).getMainPanel().setBorder(blueline);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for( int i=0 ; i<friendsPanel.size() ; i++ ){
            if( e.getSource()== friendsPanel.get(i).getSongInformaton() ){
                /*
                play song
                 */
            }
            if( e.getSource()== friendsPanel.get(i).getFriendName()){
                /*
                show sharedplaylist of friend
                 */
            }
        }
    }


}
