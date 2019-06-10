package other;

import com.sun.javaws.jnl.JARDesc;
import home.JPotifyGUI;
import other.PlayList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DisplayLists extends JScrollPane {

    private JPanel mainPanel;
    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
    private ArrayList<JButton> playlistsbtn = new ArrayList<JButton>();
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Albume> albumes = new ArrayList<Albume>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();


    public DisplayLists(JPotifyGUI gui) {
        mainPanel = new JPanel();
        setViewportView(mainPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        PlayList p1 = new PlayList("1");
        playlists.add(p1);
        PlayList p2 = new PlayList("2");
        playlists.add(p2);
        PlayList p3 = new PlayList("3");
        playlists.add(p3);
        PlayList p4 = new PlayList("4");
        playlists.add(p4);
        PlayList p5 = new PlayList("5");
        playlists.add(p5);
        PlayList p6 = new PlayList("6");
        playlists.add(p6);
        PlayList p7 = new PlayList("7");
        playlists.add(p7);
        PlayList p8 = new PlayList("8");
        playlists.add(p8);
        PlayList p9 = new PlayList("9");
        playlists.add(p9);
        PlayList p10 = new PlayList("10");
        playlists.add(p10);
        GridLayout layout = new GridLayout(3 + playlists.size() , 1);
        mainPanel.setLayout(layout);
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("Playlists :");
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        mainPanel.add(songsbutton);
        mainPanel.add(albumebutton);
        mainPanel.add(playlistlbl);
        if(playlists.size() != 0){
            for(PlayList pl : playlists){
                JButton plbtn = new JButton(pl.getPlayListName());
                plbtn.setBackground(Color.WHITE);
                plbtn.setPreferredSize(new Dimension(200 , 100));
                mainPanel.add(plbtn);
                playlistsbtn.add(plbtn);
            }
        }
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.white);
        Border blackline = BorderFactory.createLineBorder(Color.GREEN);
        mainPanel.setBorder(blackline);
    }

    public void addSong(Song s){
        songs.add(s);
        boolean albumeExists = false;
        for(Albume a : albumes){
            if(a.getAlbumeName().equals(s.getAlbumnane())){
                a.addSong(s);
                albumeExists = true;
                break;
            }
        }
        if(albumeExists == false){
            Albume albume = new Albume(s , s.getAlbumnane());
            addAlbum(albume);
        }
    }

    public void addAlbum(Albume albume){
        albumes.add(albume);
    }

    public void addPlaylist(String playlistName){
        PlayList playList = new PlayList(playlistName);
        playlists.add(playList);
    }

    public void addSongToPlaylist(Song song , PlayList playList){
        for(PlayList p : playlists){
            if(p.equals(playList)){
                p.addSong(song);
                break;
            }
        }
    }

    public void removeSong(Song s){
        if(s.getAlbume().getSongs().size() == 1){
            removeAlbum(s.getAlbume());
        }
        else{
            s.getAlbume().deletSong(s);
        }
        songs.remove(s);
    }

    public void removeAlbum(Albume a){
        albumes.remove(a);
    }

    public void removePlaylist(PlayList pl){
        playlists.remove(pl);
    }
}
