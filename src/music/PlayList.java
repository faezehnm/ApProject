package music;

import music.Song;

import java.util.ArrayList;

public class PlayList {

    private String playListName;
    private ArrayList<Song> songs;

    public PlayList(String playListName){
        this.playListName = playListName;
        songs = new ArrayList<Song>();
    }

    public String getPlayListName(){
        return playListName;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

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
}
