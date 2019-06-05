package FirendsActivity;

import home.JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FriendsActivityGUI extends JScrollPane implements ActionListener {

    private JPanel mainPanel;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private ArrayList<JPanel> firendsPanel = new ArrayList<JPanel>();
    private ImageIcon imSpeaker3 =new ImageIcon("src/Icons/speaker3.png");

    public FriendsActivityGUI(JPotifyGUI gui){
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        mainPanel.setLayout(null);

        for( int i=0 ; i<friends.size() ; i++ ){
            setFirendsPanel(friends.get(i));
        }
    }

    public void setFirendsPanel(Friend friend ){

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JPanel mainPanelUp = new JPanel(new GridLayout(1,2));
        JPanel mainPanelDown = new JPanel();

        JLabel friendName = new JLabel(friend.getName());

        JLabel lastTime = new JLabel();
        if(friend.isSongPlaying())
            lastTime.setIcon(imSpeaker3);
        if(!friend.isSongPlaying())
            lastTime.setText(friend.getLastTimeSongPlaying());

        JButton songInformation = new JButton(friend.getLastSong().getMusicName()+"\n"+friend.getLastSong().getArtist()+"\n"+friend.getLastSong().getAlbumnane());
        songInformation.addActionListener(this);

        mainPanelUp.add(friendName);
        mainPanelUp.add(lastTime) ;
        mainPanelDown.add(songInformation);
        mainPanel.add(mainPanelUp);
        mainPanel.add(mainPanelDown);

        firendsPanel.add(mainPanel);

    }

    public void addFriend(Friend friend){
        friends.add(friend);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for( int i=0 ; i<firendsPanel.size() ; i++ ){
            if( e.getSource()== firendsPanel.get(i)){
                /*
                play song
                 */
            }
        }
    }


}
