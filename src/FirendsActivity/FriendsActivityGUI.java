package FirendsActivity;

import home.JPotifyGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsActivityGUI extends JScrollPane implements ActionListener {

    private JPanel mainPanel;
    private ArrayList<FriendPanel> friendsPanel = new ArrayList<FriendPanel>();

    private JPanel topPanel ;
    private JLabel jLabel =  new JLabel("Friend Activity") ;
    private ImageIcon imgAdd = new ImageIcon("src/Icons/plus.png") ;
    private JButton btnAddFriend = new JButton(imgAdd);


    public FriendsActivityGUI(JPotifyGUI gui)
    {

        mainPanel = new JPanel(new GridLayout(0,1));
        setViewportView(mainPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);

        btnAddFriend.addActionListener(this);
        topPanel = new JPanel();
        topPanel.add(jLabel);
        topPanel.add(btnAddFriend) ;
        mainPanel.add(topPanel);


    }

    public void creatFirendPanel()
    {
        for( int i=0 ; i<Friend.getFriends().size() ; i++ ){
            friendsPanel.add(new FriendPanel(Friend.getFriends().get(i)));
        }
        addFriendsPanel();
    }

    private void addFriendsPanel()
    {
        for( int i=0 ; i<friendsPanel.size() ; i++ ){
            mainPanel.add(friendsPanel.get(i).getMainPanel());
            Border blueline = BorderFactory.createLineBorder(Color.CYAN.brighter());
            friendsPanel.get(i).getMainPanel().setBorder(blueline);
        }
    }

    public void removeFrendsPanel(){
        for( int i=0 ; i<friendsPanel.size() ; i++ ){
            mainPanel.remove(friendsPanel.get(i).getMainPanel());
        }
    }

    public void ActionToAddFriend(){
        new AddFriendGUI() ;
        removeFrendsPanel();
        creatFirendPanel();
        /*
        action for network
         */
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if( e.getSource()== btnAddFriend ){
            ActionToAddFriend();
        }

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
