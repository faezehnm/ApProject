package other;

import home.JPotifyGUI;
import playControl.PlayMusicGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.javafx.fxml.expression.Expression.add;

public class DisplayListsControl {

    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Albume> albumes = new ArrayList<Albume>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();
    private JPotifyGUI mainGUI;
    private JScrollPane scrollPane = null;

    public DisplayListsControl(JPotifyGUI mainGUI)throws Exception{
        Song s1 = new Song("src/songs/Mohammad Alizadeh - Khateret Takht [128].mp3");
        addSong(s1);
        Song s2 = new Song("src/songs/Mehdi Yarrahi - Sarma Nazdike (128).mp3");
        addSong(s2);
        Song s3 = new Song("src/songs/Mehdi Yarrahi – Mesle Mojasameh128 (UpMusic).mp3");
        addSong(s3);
        Song s4 = new Song("src/songs/Ashvan - Mano Daryab.mp3");
        addSong(s4);
        Song s5 = new Song("src/songs/Mohsen_Sharifian-Mahalleye_Khomooni-(WWW.IRMP3.IR).mp3");
        addSong(s5);
        Song s6 = new Song("src/songs/Ashvan - Daram Ashegh Misham (128).mp3");
        addSong(s6);
        Song s7 = new Song("src/songs/Happier.mp3");
        addSong(s7);
//        Song s8 = new Song("src/songs/03 Dar Astaneye Piri [320].mp3");
//        addSong(s8);
//        Song s9 = new Song("src/songs/02 To Dar Masafate Barani [320].mp3");
//        addSong(s9);
        PlayList p1 = new PlayList("p1");
        playlists.add(p1);
        PlayList p2 = new PlayList("p2");
        playlists.add(p2);
        PlayList p3 = new PlayList("p3");
        playlists.add(p3);
        PlayList p4 = new PlayList("p4");
        playlists.add(p4);
        PlayList p5 = new PlayList("p5");
        playlists.add(p5);
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

    public void setDisplaySongs(ArrayList<Song> songs , PlayMusicGUI playMusicGUI){
        DisplaySongs displaySongs = new DisplaySongs(songs , playMusicGUI);
        if(scrollPane != null){
            mainGUI.remove(scrollPane);
        }
        scrollPane = displaySongs;
        mainGUI.getContentPane().add(scrollPane);
        mainGUI.add(scrollPane , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    public void setDisplayAlbums(PlayMusicGUI playMusicGUI){
        DisplayAlbumes displayAlbumes = new DisplayAlbumes(albumes ,this ,  playMusicGUI);
        if(scrollPane != null) {
            mainGUI.remove(scrollPane);
        }
        scrollPane = displayAlbumes;
        mainGUI.getContentPane().add(scrollPane);
        mainGUI.add(scrollPane , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    public void setDisplayPlaylist(PlayList playlist , PlayMusicGUI playMusicGUI){
        DisplaySongs displaySongs = new DisplaySongs(playlist.getSongs() , playMusicGUI);
        if(scrollPane != null){
            mainGUI.remove(scrollPane);
        }
        scrollPane = displaySongs;
        mainGUI.getContentPane().add(scrollPane);
        mainGUI.add(scrollPane , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }
}
