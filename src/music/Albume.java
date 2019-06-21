package music;

import music.Song;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Albume extends Music{

    private ArrayList<Song> songs;

    public Albume(Song song , String albumeName){
        songs = new ArrayList<Song>();
        this.name = albumeName;
        songs.add(song);
        artwork = song.getArtwork();
    }


    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void addSong(Song song){
        if(! songs.contains(song)){
            songs.add(song);
        }
    }

    /*public void deletSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
        }
    }*/


    /*public void setArtwork(){
        artwork = songs.get(0).getArtwork();
    }*/
}
