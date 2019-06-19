package other;
import home.JPotifyGUI;

import javax.swing.*;
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


    public DisplayListsGUI(JPotifyGUI mainGUI) throws Exception{
        displayListsControl = new DisplayListsControl(mainGUI);
        this.mainGUI = mainGUI;
        //JLabel label = new JLabel("ahmagh");
        //mainGUI.add(label);
        listsPnl = new JPanel();
        creatListsPnl();
    }

    private void creatListsPnl(){
        setViewportView(listsPnl);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridLayout layout = new GridLayout(5 + displayListsControl.getPlaylists().size() , 1);
        listsPnl.setLayout(layout);
        addNewMusic = new JButton("Add music");
        songsbutton = new JButton("Songs");
        songsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.setDisplaySongs();
            }
        });
        albumebutton = new JButton("Albums");
        albumebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.setDisplayAlbums();
            }
        });
        playlistlbl = new JLabel("Playlists :");
        addNewMusic.setPreferredSize(new Dimension(200 , 100));
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        listsPnl.add(addNewMusic);
        listsPnl.add(songsbutton);
        listsPnl.add(albumebutton);
        listsPnl.add(playlistlbl);
        if(displayListsControl.getPlaylists().size() != 0){
            for(PlayList pl : displayListsControl.getPlaylists()){
                JButton plbtn = new JButton(pl.getPlayListName());
                plbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayListsControl.setDisplayPlaylist(pl);
                    }
                });
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
        JLabel label = new JLabel("ahmagh");
        mainGUI.add(label);
    }
}
