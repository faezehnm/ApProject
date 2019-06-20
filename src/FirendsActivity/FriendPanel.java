package FirendsActivity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FriendPanel implements ActionListener {
    private Friend friend ;
    private JPanel mainPanel ;
    private JPanel mainPanelUp;
    private JPanel mainPanelDown;
    private JButton friendName ;
    private JLabel lastTime ;
    private JButton songInformaton ;
    private ImageIcon imSpeaker3 =new ImageIcon("src/Icons/speaker3.png");
    private Border noline = new EmptyBorder(10, 10, 10, 10);

    public FriendPanel(Friend friend)
    {


        this.friend= friend ;

        //mainPanelUp = new JPanel(new GridLayout(1,2));
        mainPanel = new JPanel(new GridLayout(2,1,0,0));
        // mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanelUp = new JPanel();
        mainPanelUp.setBackground(Color.white);
        mainPanelDown = new JPanel();
        mainPanelDown.setBackground(Color.white);

        friendName = new JButton(friend.getName());
        friendName.setPreferredSize(new Dimension(140,30));
        friendName.setBackground(Color.white);
        friendName.addActionListener(this);
        friendName.setBorder(noline);

        lastTime = new JLabel();
        setTimeIcon(lastTime);

        songInformaton = new JButton("<html>"+friend.getLastSong().getMusicName()+"<br>"+friend.getLastSong().getArtist()+"<br>"+friend.getLastSong().getAlbumnane()+"<html>");
        songInformaton.setBackground(Color.white);
        songInformaton.addActionListener(this);
        songInformaton.setBorder(noline);

        mainPanelUp.add(friendName);
        mainPanelUp.add(lastTime) ;
        mainPanelDown.add(songInformaton);

        mainPanel.add(mainPanelUp);
        mainPanel.add(mainPanelDown);

    }

    public Friend getFriend() {
        return friend;
    }

    public JPanel getMainPanelUp() {
        return mainPanelUp;
    }

    public JPanel getMainPanelDown() {
        return mainPanelDown;
    }

    public JButton getFriendName() {
        return friendName;
    }

    public JLabel getLastTime() {
        return lastTime;
    }

    public JButton getSongInformaton() {
        return songInformaton;
    }

    public void setTimeIcon(JLabel jLabel)
    {
        if(friend.isSongPlaying())
            jLabel.setIcon(imSpeaker3);
        if(!friend.isSongPlaying())
            jLabel.setText(friend.getLastTimeSongPlaying());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
