package FirendsActivity;

import other.PlayList;
import other.Song;

import java.util.ArrayList;

public class Friend {
    private String IP ;
    private String name ;
    private PlayList sharedPlayList ;
    private Song lastSong ;

    public Friend( String IP , String name ){
        this.IP = IP ;
        this.name = name ;
    }

    public String getIP() {
        return IP;
    }

    public String getName() {
        return name;
    }

    public void setIp(String iP) {
        this.IP = iP;
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

    public boolean isSongPlaying(){
        /*
        :)
         */
        return false ;
    }

    public String getLastTimeSongPlaying(){
        String res="0" ;
        /*
        find Last Time :)
         */
        return res;
    }
}
