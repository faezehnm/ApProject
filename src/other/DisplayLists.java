package other;

import com.sun.deploy.ref.AppModel;
import com.sun.javaws.jnl.JARDesc;
import other.PlayList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayLists extends JPanel {

    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
    private ArrayList<JButton> playlistsbtn;
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Albume> albumes = new ArrayList<Albume>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();


    public DisplayLists() {
        setLayout(new GridLayout(10 , 1));
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("Playlists :");
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        add(songsbutton);
        add(albumebutton);
        add(playlistlbl);
        if(playlists.size() != 0){
            for(PlayList pl : playlists){
                JButton plbtn = new JButton(pl.getPlayListName());
                plbtn.setBackground(Color.WHITE);
                plbtn.setPreferredSize(new Dimension(200 , 100));
                add(plbtn);
                playlistsbtn.add(plbtn);
            }
        }
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.white);
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
}
