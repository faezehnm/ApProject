package Network;

import FirendsActivity.Friend;
import home.JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Request extends JFrame implements ActionListener {
    private static final int WIDTH = 500 , HEIGHT = 200;
    private JLabel jLabel ;
    private JButton accept;
    private JButton reject;
    private ForServer forServer;
    private JPotifyGUI jPotifyGUI ;

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

    private void acceptAction() throws IOException
    {

        Friend friend = new Friend(forServer.getUser().getName());
        jPotifyGUI.getUser().addFriend(friend);
        jPotifyGUI.getUser().setCurrentFriend(friend);
        convertAllSongsToMP3();

        forServer = new ForServer(7,jPotifyGUI.getUser());
        Network network = new Network(forServer);
        new Thread(network).start();

        new SendLastSong(jPotifyGUI.getUser());

        setVisible(false);
    }

    private void rejectAction() throws IOException
    {
        User user = new User(jPotifyGUI.getUser().getName(),"null");

        forServer = new ForServer(8,user);

        Network network = new Network(forServer);
        new Thread(network).start();

        setVisible(false);
    }

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

    private void convertAllSongsToMP3() throws IOException
    {
        if(jPotifyGUI.getUser().getSharedPlaylist().getSongs().size() !=0 ) {
            for (int i = 0; i < jPotifyGUI.getUser().getSharedPlaylist().getSongs().size(); i++) {
                jPotifyGUI.getUser().getSharedPlaylist().getSongs().get(i).convertToByteArray();
            }
        }
    }
}
