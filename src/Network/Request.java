package Network;

import FirendsActivity.Friend;
import home.JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *  when user receive a follow request form other user
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class Request extends JFrame implements ActionListener {
    private static final int WIDTH = 500 , HEIGHT = 200;
    private JLabel jLabel ;
    private JButton accept;
    private JButton reject;
    private ForServer forServer;
    private JPotifyGUI jPotifyGUI ;

    /**
     * creat Request panel
     * @param forServer message from server
     * @param jPotifyGUI JPotify GUI
     */
    public Request(ForServer forServer, JPotifyGUI jPotifyGUI)
    {
        super();
        this.forServer = forServer ;
        this.jPotifyGUI = jPotifyGUI;
        setLayout(new GridLayout(3,1));
        jLabel= new JLabel(forServer.getUser().getName() + " wants to follow you .");
        accept = new JButton("accept");
        accept.addActionListener(this);
        reject = new JButton("reject");
        reject.addActionListener(this);
        add(jLabel);
        add(accept);
        add(reject);

        setSize(WIDTH, HEIGHT);
        setTitle("Request");
        setVisible(true);

    }

    /**
     * accept follow request of a user
     * @throws IOException
     */
    private void acceptAction() throws IOException
    {

        Friend friend = new Friend(forServer.getUser().getName());
        jPotifyGUI.getUser().addFriend(friend);
        jPotifyGUI.getUser().setCurrentFriend(friend);
        convertAllSongsToByte();

        forServer = new ForServer(7,jPotifyGUI.getUser());
        Network network = new Network(forServer);
        new Thread(network).start();

        new SendLastSong(jPotifyGUI.getUser());

       // jPotifyGUI.getUser().removeFriend(friend);
        setVisible(false);
    }

    /**
     * reject follow request of a user
     * @throws IOException
     */
    private void rejectAction() throws IOException
    {
        User user = new User(jPotifyGUI.getUser().getName(),"null");

        forServer = new ForServer(8,user);

        Network network = new Network(forServer);
        new Thread(network).start();

        setVisible(false);
    }

    /**
     * action to buttons
     * @param e when click on buttons
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==accept){
            System.out.println("***");
            try {
                acceptAction();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource()==reject){
            try {
                rejectAction();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * when accept follow requst , prepare all songs in shredPlaylist to send
     * @throws IOException
     */
    private void convertAllSongsToByte() throws IOException
    {
        if(jPotifyGUI.getUser().getSharedPlaylist() != null) {
            if (jPotifyGUI.getUser().getSharedPlaylist().getSongs().size() != 0) {
                for (int i = 0; i < jPotifyGUI.getUser().getSharedPlaylist().getSongs().size(); i++) {
                    jPotifyGUI.getUser().getSharedPlaylist().getSongs().get(i).toByteArray();
                }
            }
        }
    }
}
