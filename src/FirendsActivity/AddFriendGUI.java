package FirendsActivity;

import other.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFriendGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 400 , HEIGHT = 200;
    private JLabel jLabelIP;
    private JLabel jLabelName;
    private JTextField jTextFieldIP;
    private JTextField jTextFieldName;
    private JButton jButton ;
    private String friendIP ;
    private String friendName ;


    public AddFriendGUI(){
        super();
        setLayout(new GridLayout(4,1));
        setVisible(true);
        setSize(WIDTH, HEIGHT);

        jLabelIP = new JLabel("Enter your friend's IP :");
        add(jLabelIP);

        jTextFieldIP = new JTextField();
        jTextFieldIP.addKeyListener(listener);
        add(jTextFieldIP);

        jLabelName = new JLabel("Enter your friend's name :");
        add(jLabelName);

        jTextFieldName = new JTextField();
        jTextFieldName.addKeyListener(listener);
        add(jTextFieldName);

        jButton = new JButton("Add :)");
        jButton.addActionListener(this);
        add(jButton);

        friendIP = new String() ;
        friendName = new String();


    }

    private void actionToDo() throws Exception {
        friendIP = jTextFieldIP.getText() ;
        friendName = jTextFieldName.getText() ;
        Friend friend = new Friend(friendIP,friendName);
        /*
        set last song for friend
         */
        Song song = new Song("src/songs/Happier.mp3");
        friend.setLastSong(song);
        Friend.addFriend(friend);
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
