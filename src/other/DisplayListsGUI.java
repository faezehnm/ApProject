package other;
import home.JPotifyGUI;
import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.sun.javafx.fxml.expression.Expression.add;


public class DisplayListsGUI extends JScrollPane {

    private DisplayListsControl displayListsControl;
    private JPanel listsPnl;
    private JButton addNewMusic;
    private JButton addNewPlaylist;
    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
    private ArrayList<JButton> playlistsbtn = new ArrayList<JButton>();
    private JPotifyGUI mainGUI;
    private PlayMusicGUI playMusicGUI;


    public DisplayListsGUI(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI) throws Exception{
        displayListsControl = new DisplayListsControl(mainGUI);
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
        listsPnl = new JPanel();
        creatListsPnl();
    }

    private void creatListsPnl(){
        setViewportView(listsPnl);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        listsPnl.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        addNewMusic = new JButton("Add music");
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("playlists :");
        addNewMusic.setPreferredSize(new Dimension(200 , 100));
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.BLUE);
        ImageIcon image = new ImageIcon("src/Icons/add2.png");
        addNewMusic.setIcon(image);
        addNewMusic.setBackground(Color.white);
        addNewMusic.setBorder(greenLIne);
        songsbutton.setBorder(greenLIne);
        albumebutton.setBorder(greenLIne);
        playlistlbl.setBorder(greenLIne);
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
                displayListsControl.setDisplaySongs(displayListsControl.getSongs() , playMusicGUI);
            }
        });
        albumebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.setDisplayAlbums(playMusicGUI);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        listsPnl.add(addNewMusic , gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        listsPnl.add(songsbutton , gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        listsPnl.add(albumebutton , gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        listsPnl.add(playlistlbl , gbc);
        int counter = 0;
        if(displayListsControl.getPlaylists().size() != 0){
            for(PlayList pl : displayListsControl.getPlaylists()){
                JButton plbtn = new JButton(pl.getPlayListName());
                plbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayListsControl.setDisplayPlaylist(pl , playMusicGUI);
                    }
                });
                plbtn.setBackground(Color.WHITE);
                plbtn.setBorder(greenLIne);
                plbtn.setPreferredSize(new Dimension(200 , 100));
                gbc.gridx = 0;
                gbc.gridy = 4 + counter;
                listsPnl.add(plbtn , gbc);
                playlistsbtn.add(plbtn);
                counter ++;
            }
        }
        gbc.gridx = 0;
        gbc.gridy = 4 + counter;
        addNewPlaylist = new JButton("new playlist");
        addNewPlaylist.setBackground(Color.white);
        addNewPlaylist.setPreferredSize(new Dimension(200 , 100));
        addNewPlaylist.setIcon(image);
        addNewPlaylist.setBorder(greenLIne);
        listsPnl.add(addNewPlaylist , gbc);
    }
}
