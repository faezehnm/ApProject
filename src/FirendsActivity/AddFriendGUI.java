package FirendsActivity;

import Network.Network;
import Network.User;
import Network.ForServer;
import music.Song;
import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class AddFriendGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 400 , HEIGHT = 200;

    private JLabel jLabelName;
    private JTextField jTextFieldName;
    private JButton jButton ;
    private String friendName ;
    private User me ;

    public AddFriendGUI(User me)
    {
        super();
        setLayout(new GridLayout(4,1));
        setVisible(true);
        setSize(WIDTH, HEIGHT);

        jLabelName = new JLabel("Enter your friend's name :");
        add(jLabelName);

        jTextFieldName = new JTextField();
        jTextFieldName.addKeyListener(listener);
        add(jTextFieldName);

        jButton = new JButton("Add :)");
        jButton.addActionListener(this);
        add(jButton);

        friendName = new String();
        this.me = me ;

    }

    private void actionToDo() throws Exception {

        friendName = jTextFieldName.getText() ;
        User user = new User(friendName,me.getName());
        System.out.println(me.getName());
        ForServer forServer = new ForServer(6,user);
        Network network22 = new Network(forServer);
        new Thread(network22).start();

        //network.sendFile(forServer);
        //Friend friend = new Friend(friendName);
        //friendsActivityGUI.getUser().getFriends(friend);

        /*
        set last song for friend
         */

//        Song song = new Song("src/songs/Happier.mp3");
//        friend.setLastSong(song);

        setVisible(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            actionToDo();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    actionToDo();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    };
}
