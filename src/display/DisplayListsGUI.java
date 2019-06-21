package display;
import home.JPotifyGUI;
import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import music.*;

import static com.sun.javafx.fxml.expression.Expression.add;


public class DisplayListsGUI extends JScrollPane {

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
    private JTextField createPlaylistTextField;
    private JButton createPlaylistButton;
    private JButton finishedCooshingBtn;
    private GridBagConstraints gbc;


    public DisplayListsGUI(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI) throws Exception{
        displayListsControl = new DisplayListsControl(mainGUI);
        playlistsbtn = new ArrayList<JButton>();
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
        listsPnl = new JPanel();
        creatListsPnl();
    }

    private void creatListsPnl() {
        setViewportView(listsPnl);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        listsPnl.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addNewMusic = new JButton("Add music");
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("playlists :");
        addNewPlaylist = new JButton("new playlist");
        createPlaylistPanel = new JPanel();
        createPlaylistTextField = new JTextField();
        createPlaylistButton = new JButton("create");
        finishedCooshingBtn = new JButton("choosing finished");
        ImageIcon image = new ImageIcon("src/Icons/add2.png");
        addNewMusic.setIcon(image);
        addNewPlaylist.setIcon(image);

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
        int counter = 0;
        if (displayListsControl.getPlaylists().size() != 0) {
            for (PlayList playList : displayListsControl.getPlaylists()) {
                JButton playlistbtn = new JButton(playList.getPlayListName());
                setPlaylistButton(playlistbtn, playList);
                gbc.gridx = 0;
                gbc.gridy = 4 + counter;
                listsPnl.add(playlistbtn, gbc);
                counter++;
            }
        }
        gbc.gridx = 0;
        gbc.gridy = 4 + counter;
        gbc.ipady = 0;       //reset to default
        gbc.weighty = 1.0;   //request any extra vertical space
        gbc.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbc.insets = new Insets(10, 0, 0, 0);  //top padding
        listsPnl.add(addNewPlaylist, gbc);
    }


    private void setColor(){
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.BLUE);
        addNewMusic.setBackground(Color.white);
        addNewPlaylist.setBackground(Color.white);
        createPlaylistPanel.setBackground(Color.WHITE);
        createPlaylistTextField.setBackground(Color.WHITE);
        createPlaylistButton.setBackground(Color.WHITE);
        finishedCooshingBtn.setBackground(Color.WHITE);
    }

    private void setBorder(){
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        addNewMusic.setBorder(greenLIne);
        songsbutton.setBorder(greenLIne);
        albumebutton.setBorder(greenLIne);
        playlistlbl.setBorder(greenLIne);
        addNewPlaylist.setBorder(greenLIne);
        createPlaylistPanel.setBorder(greenLIne);
        createPlaylistTextField.setBorder(greenLIne);
        createPlaylistButton.setBorder(greenLIne);
        finishedCooshingBtn.setBorder(greenLIne);
    }

    private void setSize(){
        addNewMusic.setPreferredSize(new Dimension(200 , 100));
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        addNewPlaylist.setPreferredSize(new Dimension(200 , 100));
        createPlaylistPanel.setPreferredSize(new Dimension(200 , 100));
        createPlaylistTextField.setPreferredSize(new Dimension(200 , 50));
        createPlaylistButton.setPreferredSize(new Dimension(200 , 50));
        finishedCooshingBtn.setPreferredSize(new Dimension(200 , 100));
    }

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
                    displayListsControl.setDisplaySongs(displayListsControl.getSongs() , playMusicGUI);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        albumebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplayAlbums(playMusicGUI);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addNewPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaylistPanel.setLayout(new GridLayout(2, 1));
                createPlaylistTextField.setText("");
                createPlaylistTextField.setEditable(true);
                createPlaylistPanel.add(createPlaylistTextField);
                createPlaylistPanel.add(createPlaylistButton);
                listsPnl.remove(addNewPlaylist);
                listsPnl.add(createPlaylistPanel , gbc);
                mainGUI.revalidate();
                mainGUI.repaint();
            }
        });

        createPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaylistTextField.setEditable(false);
                PlayList playList = displayListsControl.addPlaylist(createPlaylistTextField.getText());
                try {
                    displayListsControl.setSelectSongs(playMusicGUI, playList);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                listsPnl.remove(createPlaylistPanel);
                listsPnl.add(finishedCooshingBtn, gbc);
            }
        });

        finishedCooshingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.clean();
                listsPnl.remove(finishedCooshingBtn);
                listsPnl.add(addNewPlaylist , gbc);
            }
        });
    }



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
        playlistbtn.setBackground(Color.WHITE);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        playlistbtn.setBorder(greenLIne);
        playlistbtn.setPreferredSize(new Dimension(200 , 100));
        playlistsbtn.add(playlistbtn);
    }
}
