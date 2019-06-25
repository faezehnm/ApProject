package music;

import music.Song;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a playlist which is a group of songs that are selected by yhe user.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class PlayList implements Serializable {

    private String playListName;
    private ArrayList<Song> songs;
    private PlaylistSituation playlistSituation;

    /**
     * Creates an object of Playlist class.
     * @param playListName is the name of new playlist
     * @param playlistSituation specifies if this playlist is permanent or temporary
     */

    public PlayList(String playListName , PlaylistSituation playlistSituation){
        this.playListName = playListName;
        this.playlistSituation = playlistSituation;
        songs = new ArrayList<Song>();
    }

    public String getPlayListName(){
        return playListName;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    /**
     * Adds a song to the playlist
     * @param song is the new song that is added to this playlist
     */

    public void addSong(Song song){
        if(! songs.contains(song)){
            songs.add(song);
        }
    }

    public void removeSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
        }
    }

    /*public int getNumberOfSongs(){
        return songs.size();
    }*/

    public PlaylistSituation getPlaylistSituation() {
        return playlistSituation;
    }

    public void setPlayListName(String name){
        playListName = name;
    }

}
