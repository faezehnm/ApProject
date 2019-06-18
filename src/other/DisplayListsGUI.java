package other;
import home.JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.javafx.fxml.expression.Expression.add;


public class DisplayListsGUI extends JScrollPane {
    private DisplayLists displayLists;
    private JPanel listsPnl;
    private JButton addNewMusic;
    private JButton addNewPlaylist;
    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
    private ArrayList<JButton> playlistsbtn = new ArrayList<JButton>();
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Albume> albumes = new ArrayList<Albume>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();


    public DisplayListsGUI() {
        displayLists = new DisplayLists();
        listsPnl = new JPanel();
        creatListsPnl();
    }

    private void creatListsPnl(){
        setViewportView(listsPnl);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridLayout layout = new GridLayout(5 + displayLists.getPlaylists().size() , 1);
        listsPnl.setLayout(layout);
        addNewMusic = new JButton("Add music");
        addNewMusic.setPreferredSize(new Dimension(200 , 100));
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("Playlists :");
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        listsPnl.add(addNewMusic);
        listsPnl.add(songsbutton);
        listsPnl.add(albumebutton);
        listsPnl.add(playlistlbl);
        if(playlists.size() != 0){
            for(PlayList pl : playlists){
                JButton plbtn = new JButton(pl.getPlayListName());
                plbtn.setBackground(Color.WHITE);
                plbtn.setPreferredSize(new Dimension(200 , 100));
                listsPnl.add(plbtn);
                playlistsbtn.add(plbtn);
            }
        }
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.white);
        ImageIcon image = new ImageIcon("src/Icons/add2.png");
        addNewMusic.setIcon(image);
        addNewMusic.setBackground(Color.white);
        addNewPlaylist = new JButton("new playlist");
        addNewPlaylist.setBackground(Color.white);
        addNewPlaylist.setIcon(image);
        listsPnl.add(addNewPlaylist);
    }
}
