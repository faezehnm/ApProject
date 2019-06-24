package display;

import music.*;
import home.JPotifyGUI;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import playControl.PlayMusicGUI;

/**
 * This class controls the left panel and it's buttons and also some of it's actionlisteners
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class DisplayListsControl {
    private ArrayList<Song> songs = new ArrayList();
    private ArrayList<Albume> albumes = new ArrayList();
    private ArrayList<PlayList> playlists = new ArrayList();
    private JPotifyGUI mainGUI;
    private DisplaySongsGroup displaySongsGroup = null;
    private PlayMusicGUI playMusicGUI;

    /**
     * Craetes an object of this class
     * @param mainGUI is an object of JPotifyGUI which this object is added to.
     * @param playMusicGUI is an object of PlayMusic GUI which is added to the south of main frame
     * @throws Exception
     */

    public DisplayListsControl(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI){
        /*Song s1 = new Song("src/songs/Mohammad Alizadeh - Khateret Takht [128].mp3");
        this.addSong(s1);
        Song s2 = new Song("src/songs/Mehdi Yarrahi - Sarma Nazdike (128).mp3");
        this.addSong(s2);
        Song s3 = new Song("src/songs/Mehdi Yarrahi – Mesle Mojasameh128 (UpMusic).mp3");
        this.addSong(s3);
        Song s4 = new Song("src/songs/Ashvan - Mano Daryab.mp3");
        this.addSong(s4);
        Song s5 = new Song("src/songs/Mohsen_Sharifian-Mahalleye_Khomooni-(WWW.IRMP3.IR).mp3");
        this.addSong(s5);
        Song s6 = new Song("src/songs/Ashvan - Daram Ashegh Misham (128).mp3");
        this.addSong(s6);
        Song s7 = new Song("src/songs/Happier.mp3");
        this.addSong(s7);
        Song s8 = new Song("src/songs/03 Dar Astaneye Piri [320].mp3");
        this.addSong(s8);
        Song s9 = new Song("src/songs/02 To Dar Masafate Barani [320].mp3");
        this.addSong(s9);
        Song s10 = new Song("src/songs/Ehaam - Bezan Baran (128).mp3");
        this.addSong(s10);
        Song s11 = new Song("src/songs/Ehaam - Haale Man (128).mp3");
        this.addSong(s11);*/
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
        setPermanentPlaylists();
    }

    /**
     * Adds the given song to the library if it wasn't added befor
     * @param s is the given song to be added.
     */

    private void addSong(Song s) {
        this.songs.add(s);
        boolean albumeExists = false;
        Iterator var3 = this.albumes.iterator();

        while(var3.hasNext()) {
            Albume a = (Albume)var3.next();
            if (a.getName().equals(s.getAlbumeName())) {
                a.addSong(s);
                s.setAlbume(a);
                albumeExists = true;
                break;
            }
        }

        if (!albumeExists) {
            Albume albume = new Albume(s, s.getAlbumeName());
            this.addAlbum(albume);
            s.setAlbume(albume);
        }

    }

    private void addAlbum(Albume albume) {
        this.albumes.add(albume);
    }

    /**
     * creates a new playlist and add it to playlists.
     * @param playlistName is the name of new playlist
     * @return the new playlist
     */

    public PlayList addPlaylist(String playlistName) {
        PlayList playList = new PlayList(playlistName , PlaylistSituation.TEMPORARY);
        this.playlists.add(playList);
        return playList;
    }

    /**
     * Adds the given song to the given playlist
     * @param song is the given song for adding.
     * @param playListName is the name of playlist that we want add a song to.
     */

    public void addSongToPlaylist(Song song, String playListName) {
        for(PlayList playList : playlists){
            if(playList.getPlayListName().equals(playListName)){
                if(! playList.getSongs().contains(song)){
                    playList.addSong(song);
                }
                break;
            }
        }
    }

    public void removePlaylist(PlayList pl) {
        this.playlists.remove(pl);
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public ArrayList<Albume> getAlbumes() {
        return this.albumes;
    }

    public ArrayList<PlayList> getPlaylists() {
        return this.playlists;
    }

    /**
     * Creates an object of DisplaySongs class and display it in the center part of GUI.
     * @param songs is the a list of songs to be displayed.
     */

    public void setDisplaySongs(ArrayList<Song> songs){
        DisplaySongs displaySongs = null;
        try {
            displaySongs = new DisplaySongs(songs, playMusicGUI, (PlayList)null , this , DisplaySongsSituation.PLAYING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.displaySongsGroup = displaySongs;
        this.mainGUI.getContentPane().add(this.displaySongsGroup);
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * Creates an object of DisplayAlbums and set it in the center part of GUI.
     */

    public void setDisplayAlbums(){
        DisplayAlbumes displayAlbumes = null;
        try {
            displayAlbumes = new DisplayAlbumes(this.albumes, this, playMusicGUI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.displaySongsGroup = displayAlbumes;
        this.mainGUI.getContentPane().add(this.displaySongsGroup);
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * Creates an object of DisplaySongs class and display songs of the given playlist in teh center part of main  frame.
     * @param playlist is a playlist that it's songs are displayed
     * @param playMusicGUI is an object of PlayMusic GUI which is added to the south of main frame
     */

    public void setDisplayPlaylist(PlayList playlist, PlayMusicGUI playMusicGUI){
        DisplaySongs displaySongs = null;
        try {
            displaySongs = new DisplaySongs(playlist.getSongs(), playMusicGUI , (PlayList)null , this , DisplaySongsSituation.PLAYING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.displaySongsGroup = displaySongs;
        this.mainGUI.getContentPane().add(this.displaySongsGroup);
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * Creates an object ChooseMusicFrame and displays a JFileChooser for selecting a mp3 file
     */

    public void addSong() {
        ChooseMusicFrame chooseMusicFrame = new ChooseMusicFrame();
        File songFile = chooseMusicFrame.getNewSong();
        if (songFile != null) {
            boolean fileExists = false;
            Iterator iterator = this.songs.iterator();

            while(iterator.hasNext()) {
                Song s = (Song)iterator.next();
                if (s.getFileAddress().equals(songFile.getPath())) {
                    fileExists = true;
                    break;
                }
            }

            if (!fileExists) {
                Song newSong = null;
                try {
                    newSong = new Song(songFile.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.addSong(newSong);
                try {
                    update(newSong);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Updates displaying musics when a new song ai added.
     * @param song is the new song
     */

    private void update(Song song){
        if(displaySongsGroup != null){
            if(displaySongsGroup instanceof DisplayAlbumes){
                setDisplayAlbums();
            }
            else if(displaySongsGroup.getMusics().size() == songs.size()){
                setDisplaySongs(songs);
            }
            else if(((Song)(displaySongsGroup.getMusics().get(0))).getAlbume().equals(song.getAlbume()) && displaySongsGroup.getMusics().size() == song.getAlbume().getSongs().size()){
                setDisplaySongs(song.getAlbume().getSongs());
            }
        }
    }

    /**
     * It creates an object of DisplaySongs class for selection situation when we want to add songs to a new playlist.
     * @param playMusicGUI is an object of PlayMusicGUI class that is added to mainGUI
     * @param playList is the new created playlist.
     */

    public void setSelectSongs(PlayMusicGUI playMusicGUI, PlayList playList){
        DisplaySongs displaySongs = null;
        try {
            displaySongs = new DisplaySongs(this.songs, playMusicGUI , playList , this , DisplaySongsSituation.SELECTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.displaySongsGroup = displaySongs;
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * It cleans the center part of GUI if some thing is displaying
     */

    public void clean() {
        if (this.displaySongsGroup != null) {
            this.mainGUI.remove(this.displaySongsGroup);
            this.mainGUI.revalidate();
            this.mainGUI.repaint();
            this.displaySongsGroup = null;
        }

    }

    /**
     * Replaces a song when it is clicked and played.
     * @param song is the played song
     */

    public void replace(Song song){
        songs.remove(song);
        songs.add(0 , song);
        Albume albume = song.getAlbume();
        albumes.remove(albume);
        albumes.add(0 , albume);
        try {
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It just updates displaying musics when a song is replaced.
     */

    private void update(){
        if(displaySongsGroup != null){
            if(displaySongsGroup instanceof DisplayAlbumes){
                setDisplayAlbums();
            }
            else if(displaySongsGroup.getMusics().size() == songs.size()){
                setDisplaySongs(songs);
            }
        }
    }

    /**
     * It creates two permanent playlist(favorite songs and shared playlist) and add them to the playlists.
     */

    private void setPermanentPlaylists(){
        PlayList share = new PlayList("Shared Playlist" , PlaylistSituation.PERMANENT);
        PlayList favorate = new PlayList("Favorite Songs" , PlaylistSituation.PERMANENT);
        playlists.add(share);
        playlists.add(favorate);
    }

    public void deletPlaylist(PlayList playList){
        playlists.remove(playList);
    }

}
