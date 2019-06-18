package other;

import home.JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.javafx.fxml.expression.Expression.add;

public class DisplayLists {

    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Albume> albumes = new ArrayList<Albume>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();

    private void addSong(Song s){
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

    private void addAlbum(Albume albume){
        albumes.add(albume);
    }

    private void addPlaylist(String playlistName){
        PlayList playList = new PlayList(playlistName);
        playlists.add(playList);
    }

    private void addSongToPlaylist(Song song , PlayList playList){
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

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public ArrayList<Albume> getAlbumes(){
        return albumes;
    }

    public ArrayList<PlayList> getPlaylists(){
        return playlists;
    }
}
