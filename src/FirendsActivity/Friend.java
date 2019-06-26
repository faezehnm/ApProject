package FirendsActivity;

import music.PlayList;
import music.PlaylistSituation;
import music.Song;

import java.io.Serializable;
import java.util.ArrayList;

public class Friend implements Serializable {

    private String name ;
    private PlayList sharedPlayList = new PlayList(name, PlaylistSituation.PERMANENT) ;
    private Song lastSong ;
    private Boolean isSongPlaying =false ;
    private int lasSongIndex  = 0;
    private int fileIndex = 0 ;

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public Friend(String name ){
        this.name = name ;
    }

    public Song getLastSong(){
        return lastSong;
    }

    public void setLastSong(){
        lastSong = sharedPlayList.getSongs().get(lasSongIndex);
    }

    public void setLasSongIndex(int lasSongIndex) {
        this.lasSongIndex = lasSongIndex;
    }

    public String getName() {
        return name;
    }

    public PlayList getSharedPlayList() {
        return sharedPlayList;
    }

    public void addSongToSharedPlayList(Song song
    ){
        sharedPlayList.addSong(song);
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

