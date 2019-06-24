package FirendsActivity;

import home.JPotifyGUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsActivityGUI extends JScrollPane implements ActionListener , Serializable {

    private GridBagConstraints gbc;
    private GridBagConstraints gbc2;
    private JPanel junk = new JPanel();
    private Border noline = new EmptyBorder(10, 10, 10, 10);

    private JPanel mainPanel;
    private ArrayList<FriendPanel> friendsPanel = new ArrayList<FriendPanel>();

    private JPanel topPanel ;
    private JLabel jLabel =  new JLabel("Friend Activity") ;
    private ImageIcon imgAdd = new ImageIcon("src/Icons/plus.png") ;
    private JButton btnAddFriend = new JButton(imgAdd);


    public FriendsActivityGUI(JPotifyGUI gui)
    {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc2 = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();

        mainPanel = new JPanel();
        mainPanel.setLayout(layout);
        setViewportView(mainPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);

        btnAddFriend.addActionListener(this);
        topPanel = new JPanel();
        topPanel.add(jLabel);
        topPanel.add(btnAddFriend) ;
        jLabel.setBackground(Color.white);
        btnAddFriend.setBackground(Color.white);
        btnAddFriend.setBorder(noline);
        topPanel.setBackground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(topPanel,gbc);


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
            gbc.gridx = 0;
            gbc.gridy = 1 + i ;
            mainPanel.add(friendsPanel.get(i).getMainPanel(),gbc);
            Border blueline = BorderFactory.createLineBorder(Color.CYAN.brighter());
            friendsPanel.get(i).getMainPanel().setBorder(blueline);
        }
        gbc2.gridx = 0;
        gbc2.gridy = 1+friendsPanel.size();
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.PAGE_END;
        gbc2.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(junk,gbc2);

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
