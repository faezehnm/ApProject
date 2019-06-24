package display;
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

public class DisplayListsGUI extends JScrollPane implements Serializable {

    private DisplayListsControl displayListsControl;
    private JPanel listsPnl;
    private JButton addNewMusic;
    private JButton addNewPlaylist;
    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
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

    public DisplayListsGUI(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI){
        try {
            displayListsControl = new DisplayListsControl(mainGUI , playMusicGUI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        playlistsbtn = new ArrayList<JButton>();
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
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
        numberOfPlaylists = 0;
        if (displayListsControl.getPlaylists().size() != 0) {
            for (PlayList playList : displayListsControl.getPlaylists()) {
                JButton playlistbtn = new JButton(playList.getPlayListName());
                setPlaylistButton(playlistbtn, playList);
                gbc.gridx = 0;
                gbc.gridy = 4 + numberOfPlaylists;
                listsPnl.add(playlistbtn, gbc);
                numberOfPlaylists++;
            }
        }
        gbc2.gridx = 0;
        gbc2.gridy = 4 + numberOfPlaylists;
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
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.WHITE);
        addNewMusic.setBackground(Color.white);
        addNewPlaylist.setBackground(Color.white);
        createPlaylistPanel.setBackground(Color.WHITE);
        finishedCooshingBtn.setBackground(Color.WHITE);
        newPlaylistTextField.setBackground(Color.WHITE);
        createNewPlaylistbtn.setBackground(Color.WHITE);
        cancelAddingPlaylist.setBackground(Color.WHITE);
    }

    /**
     * sets the border of all components on the panel
     */

    private void setBorder(){
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        addNewMusic.setBorder(greenLIne);
        songsbutton.setBorder(greenLIne);
        albumebutton.setBorder(greenLIne);
        playlistlbl.setBorder(greenLIne);
        addNewPlaylist.setBorder(greenLIne);
        createPlaylistPanel.setBorder(greenLIne);
        finishedCooshingBtn.setBorder(greenLIne);
        createNewPlaylistbtn.setBorder(greenLIne);
        newPlaylistTextField.setBorder(greenLIne);
        cancelAddingPlaylist.setBorder(greenLIne);
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
                    displayListsControl.setDisplaySongs(displayListsControl.getSongs());
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
                newPlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                gbc.gridy = numberOfPlaylists + 4;
                JButton newPlaylist = new JButton(displayListsControl.getPlaylists().get(numberOfPlaylists).getPlayListName());
                setPlaylistButton(newPlaylist , displayListsControl.getPlaylists().get(numberOfPlaylists));
                listsPnl.add(newPlaylist , gbc);
                numberOfPlaylists ++;
                gbc2.gridy = 4 + numberOfPlaylists;
                listsPnl.add(addNewPlaylist, gbc2);
                mainGUI.revalidate();
                mainGUI.repaint();
            }
        });
    }

    /**
     * Adjust button for each playlist and adds mouseistener and actionlistener to it
     * @param playlistbtn is the button for a specified playlist.
     * @param playList is a playlist that the button belongs to.
     */

    private void setPlaylistButton(JButton playlistbtn , PlayList playList){
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
            popupMenu.add(delet);
            popupMenu.add(rename);
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
                    renameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        }
        playlistbtn.setBackground(Color.WHITE);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        playlistbtn.setBorder(greenLIne);
        playlistbtn.setPreferredSize(new Dimension(200 , 100));
        playlistsbtn.add(playlistbtn);
    }
}
