package FirendsActivity;

import Network.Network;
import Network.User;
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

public class FriendsActivityGUI extends JScrollPane implements ActionListener{

    private GridBagConstraints gbc;
    private GridBagConstraints gbc2;
    private JPanel junk;
    private Border noline;

    private JPanel mainPanel;
    private ArrayList<FriendPanel> friendsPanel;
    private JPanel topPanel ;
    private JLabel jLabel;
    private ImageIcon imgAdd;
    private JButton btnAddFriend ;
    private User user;
    private AddFriendGUI addFriendGUI ;
    private int current = 0;
    private Border blackLine;

    public FriendsActivityGUI(User user)
    {
        this.user = user ;

        junk = new JPanel();
        noline = new EmptyBorder(10, 10, 10, 10);
        friendsPanel = new ArrayList<FriendPanel>();
        jLabel =  new JLabel("Friend Activity") ;
        imgAdd = new ImageIcon("src/Icons/bplus(2).png") ;
        btnAddFriend = new JButton(imgAdd);
        user = new User("null","null");
        blackLine = BorderFactory.createLineBorder(Color.BLACK);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc2 = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();

        mainPanel = new JPanel();
        mainPanel.setLayout(layout);
        setViewportView(mainPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        Border blueLIne = BorderFactory.createLineBorder(Color.CYAN);
        setBorder(blueLIne);
        mainPanel.setBackground(Color.BLACK);
        junk.setBackground(Color.BLACK);

        btnAddFriend.addActionListener(this);
        topPanel = new JPanel();
        topPanel.add(jLabel);
        topPanel.add(btnAddFriend) ;
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.WHITE);
        btnAddFriend.setBackground(Color.BLACK);
        btnAddFriend.setBorder(blueLIne);
        topPanel.setBackground(Color.BLACK);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(topPanel,gbc);
        btnAddFriend.setBorder(blackLine);

        gbc2.gridx = 0;
        gbc2.gridy = 1+friendsPanel.size();
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.PAGE_END;
        gbc2.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(junk,gbc2);

    }

    public void creatFirendPanel()
    {
        System.out.println("in creat friend Panel :" + user.getFriends().size());
        for( int i=current ; i<user.getFriends().size() ; i++ ){
            friendsPanel.add(new FriendPanel(user.getFriends().get(i)));
        }
        current=user.getFriends().size();

        addFriendsPanel();
    }

    private void addFriendsPanel()
    {

        for( int i=0 ; i<friendsPanel.size() ; i++ ){
            gbc.gridx = 0;
            gbc.gridy = 1 + i ;
            mainPanel.add(friendsPanel.get(i).getMainPanel(),gbc);
            Border blueline = BorderFactory.createLineBorder(Color.CYAN);
            friendsPanel.get(i).getMainPanel().setBorder(blueline);
        }
    }

    public void removeFrendsPanel()
    {
        for( int i=0 ; i<friendsPanel.size() ; i++ ){
            mainPanel.remove(friendsPanel.get(i).getMainPanel());
        }
    }

    public void ActionToAddFriend()
    {
        addFriendGUI = new AddFriendGUI(user);
        //creatFirendPanel();
        /*
        action for network
         */
    }

    public AddFriendGUI getAddFriendGUI() {
        return addFriendGUI;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<FriendPanel> getFriendsPanel() {
        return friendsPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

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
