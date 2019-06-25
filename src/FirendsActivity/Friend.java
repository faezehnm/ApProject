package FirendsActivity;

import music.PlayList;
import music.Song;

import java.util.ArrayList;

public class Friend {

    private String name ;
    private PlayList sharedPlayList ;
    private Song lastSong ;
    private Boolean isSongPlaying =false ;

    public Friend( String name ){
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public PlayList getSharedPlayList() {
        return sharedPlayList;
    }

    public void setSharedPlayList(PlayList sharedPlayList) {
        this.sharedPlayList = sharedPlayList;
    }

    public Song getLastSong() {
        return lastSong;
    }

    public void setLastSong(Song lastSong) {
        this.lastSong = lastSong;
    }

    public Boolean getSongPlaying() {
        return isSongPlaying;
    }

    public void setSongPlaying(Boolean songPlaying) {
        isSongPlaying = songPlaying;
    }

    public String getLastTimeSongPlaying(){
        String res="0" ;
        /*
        find Last Time :)
         */
        return res;
    }
}

