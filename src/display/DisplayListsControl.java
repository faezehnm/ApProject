package display;

import Network.User;
import music.*;
import home.JPotifyGUI;

import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import playControl.PlayMusicGUI;

import javax.swing.*;

/**
 * This class controls the left panel and it's buttons and also some of it's actionlisteners
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class DisplayListsControl{
    private ArrayList<Song> songs = new ArrayList();
    private ArrayList<Albume> albumes = new ArrayList();
    private ArrayList<PlayList> playlists = new ArrayList();
    private JPotifyGUI mainGUI;
    private JScrollPane scrollPane = null;
    private PlayMusicGUI playMusicGUI;
    private DisplayListsGUI displayListsGUI;

    /**
     * Craetes an object of this class
     * @param mainGUI is an object of JPotifyGUI which this object is added to.
     * @param playMusicGUI is an object of PlayMusic GUI which is added to the south of main frame
     * @throws Exception
     */

    public DisplayListsControl(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI , boolean setPlaylist , DisplayListsGUI displayListsGUI) throws Exception {
        this.displayListsGUI = displayListsGUI;
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
        if(setPlaylist) {
            setPermanentPlaylists();
        }
    }

    public void addAlbum(Albume albume) {
        this.albumes.add(albume);
    }

    /**
     * َAdds a playlist to the list of playlists and makes a button for it.It is used for deserialization
     * @param playList is the given playlist
     */

    public void addPlaylist(PlayList playList){
        playlists.add(playList);
        JButton btn = new JButton(playList.getPlayListName());
        displayListsGUI.setGBC(btn);
        displayListsGUI.setPlaylistButton(btn , playList);
    }

    public void addSong(Song s){
        songs.add(s);
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

    public void setDisplaySongs(ArrayList<Song> songs , boolean deletable){
        DisplaySongs displaySongs = null;
        try {
            displaySongs = new DisplaySongs(songs, playMusicGUI, (PlayList)null , this , DisplaySongsSituation.PLAYING , mainGUI , deletable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.scrollPane = displaySongs;
        this.mainGUI.getContentPane().add(this.scrollPane);
        this.mainGUI.add(this.scrollPane, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * Creates an object of DisplayAlbums and set it in the center part of GUI.
     */

    public void setDisplayAlbums(){
        DisplayAlbumes displayAlbumes = null;
        try {
            displayAlbumes = new DisplayAlbumes(this.albumes, this, playMusicGUI , mainGUI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.scrollPane = displayAlbumes;
        this.mainGUI.getContentPane().add(this.scrollPane);
        this.mainGUI.add(this.scrollPane , "Center");
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
            displaySongs = new DisplaySongs(playlist.getSongs(), playMusicGUI , playlist , this , DisplaySongsSituation.PLAYING , mainGUI , true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.scrollPane = displaySongs;
        this.mainGUI.getContentPane().add(this.scrollPane);
        this.mainGUI.add(this.scrollPane , "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * Creates an object ChooseMusicFrame and displays a JFileChooser for selecting a mp3 file and then adds
     * the sselected song to the library if it wasn't added befor.
     */

    public void addSong() {
        ChooseMusicFrame chooseMusicFrame = new ChooseMusicFrame();
        File[] songFiles = chooseMusicFrame.getNewSong();
        for(File songFile : songFiles) {
            if (songFile != null) {
                boolean fileExists = false;
                Iterator iterator = this.songs.iterator();

                while (iterator.hasNext()) {
                    Song s = (Song) iterator.next();
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
                    this.songs.add(newSong);
                    boolean albumeExists = false;
                    Iterator var3 = this.albumes.iterator();

                    while (var3.hasNext()) {
                        Albume a = (Albume) var3.next();
                        if (a.getName().equals(newSong.getAlbumeName())) {
                            a.addSong(newSong);
                            newSong.setAlbume(a);
                            albumeExists = true;
                            break;
                        }
                    }

                    if (!albumeExists) {
                        Albume albume = new Albume(newSong, newSong.getAlbumeName());
                        this.addAlbum(albume);
                        newSong.setAlbume(albume);
                    }
                    // this.addSong(newSong);
                    try {
                        update(newSong);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Updates displaying musics when a new song ai added.
     * @param song is the new song
     */

    private void update(Song song){
        if(scrollPane != null && scrollPane instanceof DisplaySongsGroup){
            DisplaySongsGroup displaySongsGroup = (DisplaySongsGroup) scrollPane;
            if(scrollPane instanceof DisplayAlbumes){
                setDisplayAlbums();
            }
            else if(((DisplaySongsGroup)scrollPane).getMusics().equals(songs)){
                setDisplaySongs(songs , true);
            }
            else if(((Song)(displaySongsGroup.getMusics().get(0))).getAlbume().equals(song.getAlbume()) && displaySongsGroup.getMusics().size() == song.getAlbume().getSongs().size()){
                setDisplaySongs(song.getAlbume().getSongs() , false);
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
            displaySongs = new DisplaySongs(this.songs, playMusicGUI , playList , this , DisplaySongsSituation.SELECTION , mainGUI , false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clean();
        this.scrollPane = displaySongs;
        this.mainGUI.add(this.scrollPane , "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    /**
     * It cleans the center part of GUI if some thing is displaying
     */

    public void clean() {
        if (this.scrollPane != null) {
            this.mainGUI.remove(this.scrollPane);
            this.mainGUI.revalidate();
            this.mainGUI.repaint();
            this.scrollPane = null;
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
        if(scrollPane != null && scrollPane instanceof DisplaySongsGroup){
            if(scrollPane instanceof DisplayAlbumes){
                setDisplayAlbums();
            }
            else if(((DisplaySongsGroup)scrollPane).getMusics().size() == songs.size()){
                setDisplaySongs(songs , true);
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

    public void deletPlaylist(PlayList playList) {
        playlists.remove(playList);
    }

    public void deletAlbume(Albume albume){
        albumes.remove(albume);
    }

    public void setFriendsPlaylistsDisplay(User user){
        clean();
        scrollPane = new FriendsPlaylistsDisplay(user , this);
        mainGUI.getContentPane().add(scrollPane);
        mainGUI.add(scrollPane , BorderLayout.CENTER);
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
