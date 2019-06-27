package display;
import Network.User;
import home.JPotifyGUI;
import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import music.*;

import static com.sun.javafx.fxml.expression.Expression.add;

/**
 *This class makes the left part of GUI and has several buttons for displaying songs , albumes and playlists.
 * And also has buttons for adding a song or a playlist.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class DisplayListsGUI extends JScrollPane{

    private User user;
    private DisplayListsControl displayListsControl;
    private JPanel listsPnl;
    private JButton addNewMusic;
    private JButton addNewPlaylist;
    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
    private JButton friendsPlaylists;
    private ArrayList<JButton> playlistsbtn;
    private JPotifyGUI mainGUI;
    private PlayMusicGUI playMusicGUI;
    private JPanel createPlaylistPanel;
    private JButton finishedCooshingBtn;
    private GridBagConstraints gbc;
    private GridBagConstraints gbc2;
    private int numberOfPlaylists;
    private  JFrame newPlaylistFrame;
    private JTextField newPlaylistTextField;
    private JButton createNewPlaylistbtn;
    private JButton cancelAddingPlaylist;

    /**
     * Makes an object of DisplayListsGUI
     * @param mainGUI is an object of JPotifyGUI which this object is added to.
     * @param playMusicGUI is an object of PlayMusicGUI class that is added to mainGUI
     */

    public DisplayListsGUI(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI , boolean stePlaylist , User user){
        try {
            displayListsControl = new DisplayListsControl(mainGUI , playMusicGUI , stePlaylist , this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        playlistsbtn = new ArrayList<JButton>();
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
        this.user = user;
        listsPnl = new JPanel();
        creatListsPnl();
    }

    /**
     * creates and adjusts all components on the left panel
     */

    private void creatListsPnl() {
        setViewportView(listsPnl);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc2 = new GridBagConstraints();
        listsPnl.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addNewMusic = new JButton("Add music");
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("playlists :");
        friendsPlaylists = new JButton("friends playlists");
        addNewPlaylist = new JButton("new playlist");
        createPlaylistPanel = new JPanel();
        finishedCooshingBtn = new JButton("choosing finished");
        ImageIcon image = new ImageIcon("src/Icons/add2.png");
        addNewMusic.setIcon(image);
        addNewPlaylist.setIcon(image);
        newPlaylistFrame = new JFrame("create new playlist");
        newPlaylistFrame.setLayout(new GridLayout(3 , 1));
        newPlaylistTextField = new JTextField();
        createNewPlaylistbtn = new JButton("create !");
        cancelAddingPlaylist = new JButton("cancel");
        newPlaylistFrame.add(newPlaylistTextField);
        newPlaylistFrame.add(createNewPlaylistbtn);
        newPlaylistFrame.add(cancelAddingPlaylist);

        setSize();
        setColor();
        setBorder();
        setTextColor();
        addActionListeners();

        gbc.gridx = 0;
        gbc.gridy = 0;
        listsPnl.add(addNewMusic, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        listsPnl.add(songsbutton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        listsPnl.add(albumebutton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        listsPnl.add(playlistlbl, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        listsPnl.add(friendsPlaylists , gbc);
        numberOfPlaylists = 0;
        if (displayListsControl.getPlaylists().size() != 0) {
            for (PlayList playList : displayListsControl.getPlaylists()) {
                JButton playlistbtn = new JButton(playList.getPlayListName());
                gbc.gridx = 0;
                gbc.gridy = 5 + numberOfPlaylists;
                listsPnl.add(playlistbtn, gbc);
                numberOfPlaylists++;
                setPlaylistButton(playlistbtn, playList);
            }
        }
        gbc2.gridx = 0;
        gbc2.gridy = 5 + numberOfPlaylists;
        gbc2.ipady = 0;       //reset to default
        gbc2.weighty = 1.0;   //request any extra vertical space
        gbc2.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbc2.insets = new Insets(10, 0, 0, 0);  //top padding
        listsPnl.add(addNewPlaylist, gbc2);
    }

    /**
     * sets the color of all components on the panel
     */

    private void setColor(){
        listsPnl.setBackground(Color.pink);
        songsbutton.setBackground(Color.CYAN);
        albumebutton.setBackground(Color.CYAN);
        playlistlbl.setBackground(Color.CYAN);
        friendsPlaylists.setBackground(Color.cyan);
        addNewMusic.setBackground(Color.CYAN);
        addNewPlaylist.setBackground(Color.CYAN);
        createPlaylistPanel.setBackground(Color.CYAN);
        finishedCooshingBtn.setBackground(Color.CYAN);
        newPlaylistTextField.setBackground(Color.CYAN);
        createNewPlaylistbtn.setBackground(Color.CYAN);
        cancelAddingPlaylist.setBackground(Color.CYAN);
    }

    /**
     * sets the border of all components on the panel
     */

    private void setBorder(){
        Border blueLine = BorderFactory.createLineBorder(Color.BLUE);
        setBorder(blueLine);
        addNewMusic.setBorder(blueLine);
        songsbutton.setBorder(blueLine);
        albumebutton.setBorder(blueLine);
        playlistlbl.setBorder(blueLine);
        addNewPlaylist.setBorder(blueLine);
        createPlaylistPanel.setBorder(blueLine);
        finishedCooshingBtn.setBorder(blueLine);
        createNewPlaylistbtn.setBorder(blueLine);
        newPlaylistTextField.setBorder(blueLine);
        cancelAddingPlaylist.setBorder(blueLine);
        friendsPlaylists.setBorder(blueLine);
    }

    /**
     * sets the color of all components on the panel
     */

    private void setSize(){
        addNewMusic.setPreferredSize(new Dimension(200 , 100));
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        addNewPlaylist.setPreferredSize(new Dimension(200 , 100));
        createPlaylistPanel.setPreferredSize(new Dimension(200 , 100));
        finishedCooshingBtn.setPreferredSize(new Dimension(200 , 100));
        newPlaylistFrame.setSize(new Dimension(200 , 300));
        newPlaylistTextField.setSize(new Dimension(200 , 100));
        createNewPlaylistbtn.setSize(new Dimension(200 , 100));
        cancelAddingPlaylist.setSize(new Dimension(200 , 100));
        friendsPlaylists.setPreferredSize(new Dimension(200 , 100));
    }

    /**
     * Adds actionlistener to each button of the panel
     */

    private void addActionListeners(){
        addNewMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.addSong();
                } catch (Exception e1) {
                }
            }
        });
        songsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplaySongs(displayListsControl.getSongs() , true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        albumebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplayAlbums();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addNewPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPlaylistTextField.setText("");
                newPlaylistTextField.setEditable(true);
                newPlaylistFrame.setVisible(true);
            }
        });

        createNewPlaylistbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(! newPlaylistTextField.getText().equals("")) {
                    newPlaylistFrame.setVisible(false);
                    PlayList playList = displayListsControl.addPlaylist(newPlaylistTextField.getText());
                    try {
                        displayListsControl.setSelectSongs(playMusicGUI, playList);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    listsPnl.remove(addNewPlaylist);
                    listsPnl.add(finishedCooshingBtn, gbc2);
                    mainGUI.revalidate();
                    mainGUI.repaint();
                }
            }
        });

        cancelAddingPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPlaylistTextField.setText("");
                newPlaylistFrame.setVisible(false);
            }
        });

        finishedCooshingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.clean();
                listsPnl.remove(finishedCooshingBtn);
                gbc.gridx = 0;
                gbc.gridy = numberOfPlaylists + 5;
                JButton newPlaylist = new JButton(displayListsControl.getPlaylists().get(numberOfPlaylists).getPlayListName());
                setPlaylistButton(newPlaylist , displayListsControl.getPlaylists().get(numberOfPlaylists));
                listsPnl.add(newPlaylist , gbc);
                numberOfPlaylists ++;
                gbc2.gridy = 5 + numberOfPlaylists;
                listsPnl.add(addNewPlaylist, gbc2);
                mainGUI.revalidate();
                mainGUI.repaint();
            }
        });
        friendsPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.setFriendsPlaylistsDisplay(user);
            }
        });
    }

    /**
     * Adjust button for each playlist and adds mouseistener and actionlistener to it
     * @param playlistbtn is the button for a specified playlist.
     * @param playList is a playlist that the button belongs to.
     */

    public void setPlaylistButton(JButton playlistbtn , PlayList playList){
        playlistbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplayPlaylist(playList , playMusicGUI);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        if(playList.getPlaylistSituation().equals(PlaylistSituation.TEMPORARY)){
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem delet = new JMenuItem("delet");
            JMenuItem rename = new JMenuItem("rename");
            JMenuItem addSong = new JMenuItem("add song");
            popupMenu.add(delet);
            popupMenu.add(rename);
            popupMenu.add(addSong);
            playlistbtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(e.getButton() == MouseEvent.BUTTON3){
                        popupMenu.show(playlistbtn , e.getX() , e.getY());
                    }
                }
            });
            delet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(displayListsControl.getScrollPane() instanceof DisplaySongsGroup){
                        DisplaySongsGroup displaySongsGroup = (DisplaySongsGroup) (displayListsControl.getScrollPane());
                        if(displaySongsGroup.getMusics().equals(playList.getSongs())){
                            displayListsControl.clean();
                        }
                    }
                    displayListsControl.deletPlaylist(playList);
                    listsPnl.remove(playlistbtn);
                    mainGUI.revalidate();
                    mainGUI.repaint();
                }
            });
            rename.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame renameFrame = new JFrame("rename");
                    renameFrame.setLayout(new GridLayout(3 , 1));
                    renameFrame.setBackground(Color.WHITE);
                    JTextField renameTextField = new JTextField();
                    renameFrame.setSize(new Dimension(300 , 200));
                    renameTextField.setSize(new Dimension(200 , 100));
                    renameTextField.setBackground(Color.WHITE);
                    renameTextField.setEditable(true);
                    JButton done = new JButton("done");
                    done.setBackground(Color.WHITE);
                    done.setSize(200 , 100);
                    JButton cancel = new JButton("cancel");
                    cancel.setBackground(Color.WHITE);
                    cancel.setSize(new Dimension(200 , 100));
                    renameFrame.add(renameTextField);
                    renameFrame.add(done);
                    renameFrame.add(cancel);
                    renameFrame.setVisible(true);
                    done.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            playList.setPlayListName(renameTextField.getText());
                            playlistbtn.setText(renameTextField.getText());
                            renameFrame.setVisible(false);
                            mainGUI.revalidate();
                            mainGUI.repaint();
                        }
                    });
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            renameFrame.setVisible(false);
                        }
                    });
                }
            });
            addSong.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        displayListsControl.setSelectSongs(playMusicGUI, playList);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    listsPnl.remove(addNewPlaylist);
                    JButton finished = new JButton("finished");
                    finished.setPreferredSize(new Dimension(200 , 100));
                    finished.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            listsPnl.remove(finished);
                            listsPnl.add(addNewPlaylist, gbc2);
                            displayListsControl.clean();
                            mainGUI.revalidate();
                            mainGUI.repaint();
                        }
                    });
                    listsPnl.add(finished, gbc2);
                    mainGUI.revalidate();
                    mainGUI.repaint();
                }
            });
        }
        playlistbtn.setBackground(Color.CYAN);
        playlistbtn.setForeground(Color.WHITE);
        Border greenLIne = BorderFactory.createLineBorder(Color.BLUE);
        playlistbtn.setBorder(greenLIne);
        playlistbtn.setPreferredSize(new Dimension(200 , 100));
        playlistsbtn.add(playlistbtn);
    }

    public DisplayListsControl getDisplayListsControl() {
        return displayListsControl;
    }

    /**
     * sets the layout and position for a playlist button.It is used in deserialization.
     * @param btn is the button of a playlist.
     */

    public void setGBC(JButton btn){
        gbc.gridx = 0;
        gbc.gridy = numberOfPlaylists + 5;
        setPlaylistButton(btn , displayListsControl.getPlaylists().get(numberOfPlaylists));
        listsPnl.remove(addNewPlaylist);
        listsPnl.add(btn , gbc);
        numberOfPlaylists ++;
        gbc2.gridy = 5 + numberOfPlaylists;
        listsPnl.add(addNewPlaylist, gbc2);
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    private void setTextColor(){
        addNewMusic.setForeground(Color.WHITE);
        songsbutton.setForeground(Color.WHITE);
        albumebutton.setForeground(Color.WHITE);
        addNewPlaylist.setForeground(Color.WHITE);
        finishedCooshingBtn.setForeground(Color.WHITE);
        createNewPlaylistbtn.setForeground(Color.WHITE);
        cancelAddingPlaylist.setForeground(Color.WHITE);
        finishedCooshingBtn.setForeground(Color.WHITE);
        friendsPlaylists.setForeground(Color.WHITE);
        playlistlbl.setForeground(Color.WHITE);
    }
}
