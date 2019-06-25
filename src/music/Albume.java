package music;

import music.Song;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Albume represnts an albume and is a group of songs with the same albume name. It has also artwork and name.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class Albume extends Music implements Serializable {

    private ArrayList<Song> songs;

    /**
     * Creates an albume object.
     * @param song is the song of this albume that is added to library.
     * @param albumeName is the name of albume.
     */

    public Albume(Song song , String albumeName){
        songs = new ArrayList<Song>();
        this.name = albumeName;
        songs.add(song);
        artwork = song.getArtwork();
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    /**
     * Adds a song to albume if that song wasn"t added befor.
     * @param song is the new song of the albume.
     */

    public void addSong(Song song){
        if(! songs.contains(song)){
            songs.add(song);
        }
    }

    public void deletSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
        }
    }


    /*public void setArtwork(){
        artwork = songs.get(0).getArtwork();
    }*/
}
