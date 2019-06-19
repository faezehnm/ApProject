package other;

import home.JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.javafx.fxml.expression.Expression.add;

public class DisplayListsControl {

    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Albume> albumes = new ArrayList<Albume>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();
    private JPotifyGUI mainGUI;

    public DisplayListsControl(JPotifyGUI mainGUI)throws Exception{
        Song s1 = new Song("src/Mohammad Alizadeh - Khateret Takht [128].mp3");
        songs.add(s1);
        this.mainGUI = mainGUI;
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

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public ArrayList<Albume> getAlbumes(){
        return albumes;
    }

    public ArrayList<PlayList> getPlaylists(){
        return playlists;
    }

    public void setDisplaySongs(){
        DisplaySongs displaySongs = new DisplaySongs(songs);
        mainGUI.getContentPane().add(displaySongs);
        mainGUI.add(displaySongs , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    public void setDisplayAlbums(){
        DisplayAlbumes displayAlbumes = new DisplayAlbumes(albumes);
        mainGUI.getContentPane().add(displayAlbumes);
        mainGUI.add(displayAlbumes , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    public void setDisplayPlaylist(PlayList playlist){
        DisplaySongs displaySongs = new DisplaySongs(playlist.getSongs());
        mainGUI.getContentPane().add(displaySongs);
        mainGUI.add(displaySongs , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }
}
