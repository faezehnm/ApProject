package FirendsActivity;

import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class FriendPanel implements ActionListener{

    private PlayMusicGUI playMusicGUI ;
    private Friend friend ;
    private JPanel mainPanel ;
    private JPanel mainPanelUp;
    private JPanel mainPanelDown;
    private JButton friendName ;
    private JLabel lastTime ;
    private JButton songInformaton ;
    private ImageIcon imSpeaker3;
    private Border noline;

    public FriendPanel(Friend friend , PlayMusicGUI playMusicGUI)
    {
        noline = new EmptyBorder(10, 10, 10, 10);
        imSpeaker3 =new ImageIcon("src/Icons/speaker3.png");
        this.friend= friend ;
        this.playMusicGUI = playMusicGUI ;


        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanelUp = new JPanel();
        mainPanelUp.setBackground(Color.BLACK);
        mainPanelDown = new JPanel();
        mainPanelDown.setBackground(Color.BLACK);

        friendName = new JButton(friend.getName());
        friendName.setForeground(Color.BLACK);
        friendName.setPreferredSize(new Dimension(140,30));
        friendName.setBackground(Color.BLACK);
        friendName.setForeground(Color.WHITE);
        friendName.addActionListener(this);
        friendName.setBorder(noline);

        lastTime = new JLabel();
        lastTime.setForeground(Color.CYAN);
        setTimeIcon(lastTime);

        songInformaton = new JButton("null");
        songInformaton.setBackground(Color.BLACK);
        songInformaton.setForeground(Color.WHITE);
        songInformaton.addActionListener(this);
        songInformaton.setBorder(noline);

        mainPanelUp.add(friendName);
        mainPanelUp.add(lastTime) ;
        mainPanelDown.add(songInformaton);


        GridBagLayout innerLayout = new GridBagLayout();
        GridBagConstraints innerGbc = new GridBagConstraints();
        mainPanel.setLayout(innerLayout);
        mainPanel.setSize(new Dimension(370 , 420));
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        mainPanel.add(mainPanelUp,innerGbc);
        innerGbc.gridx = 0;
        innerGbc.gridy = 1;
        mainPanel.add(mainPanelDown,innerGbc);


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
       // if( friend.getLastTime().equals("0"))
            jLabel.setIcon(imSpeaker3);
//        else
//            jLabel.setText(friend.getLastTime());
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void updateLastSongInformaion()
    {
        songInformaton.setText("<html>"+friend.getLastSong().getName()+"<br>"+friend.getLastSong().getArtist()+"<br>"+friend.getLastSong().getAlbumeName()+"<html>");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()== songInformaton ){
            try {
                playMusicGUI.setSong(friend.getLastSong(),false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

}
