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

import static java.lang.Thread.sleep;


/**
 *  each friend panel that should shows in FriendActivityGUI
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class FriendPanel implements ActionListener,Runnable{

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
    private long startTime ;
    private long endTime ;
    private int counter ;

    /**
     * creat friend panel
     * @param friend  owner of this panel
     * @param playMusicGUI south panel of JPotifyGUI
     */
    public FriendPanel(Friend friend , PlayMusicGUI playMusicGUI)
    {
        noline = new EmptyBorder(10, 10, 10, 10);
        imSpeaker3 =new ImageIcon("src/Icons/speaker4.png");
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



        lastTime.setText("");


        songInformaton = new JButton("...no song yet...");
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

    /**
     * set last time of last song which exist in friend's sharedPlay list and friend listend that
     */
    public void setTimeIcon()
    {
        endTime = System.nanoTime();
        long duration = (endTime - startTime);


        if( duration/1000000000 <= 180 ) {
            lastTime.setText("");
            lastTime.setIcon(imSpeaker3);
        }

        else  {
            lastTime.setIcon(null);

               int time = (int) (duration/1000000000);
               int timeInMin = time/60 ;

               if( timeInMin <60 )
                    lastTime.setText(timeInMin+ "m");
               else if( timeInMin>= 60) {
                   int timeInHour = timeInMin / 60;
                   lastTime.setText(timeInHour+ "h");
               }

        }


    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    /**
     * update informaton of friend's last song
     */
    public void updateLastSongInformaion()
    {
        if( friend.getLastSong() != null ) {
            startTime = System.nanoTime();
            songInformaton.setText("<html>" + friend.getLastSong().getName() + "<br>" + friend.getLastSong().getArtist() + "<br>" + friend.getLastSong().getAlbumeName() + "<html>");
            lastTime.setIcon(imSpeaker3);

            new Thread(this).start();
        }
    }

    /**
     * play frind's last song when click on that
     * @param e click on friend's last song information's button
     */
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

    /**
     * update last song's time
     */
    @Override
    public void run()
    {
        while (true) {
            try {
                sleep(1000);
                setTimeIcon();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
