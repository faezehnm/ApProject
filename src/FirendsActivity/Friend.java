//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package FirendsActivity;

import java.io.Serializable;
import music.PlayList;
import music.PlaylistSituation;
import music.Song;

public class Friend implements Serializable {
    private int port ;
    private String name;

    private PlayList sharedPlayList;
    private Song lastSong;
    private String lastTime;
    private int lasSongIndex;
    private int fileIndex;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getFileIndex() {
        return this.fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public Friend(String name) {
        this.sharedPlayList = new PlayList(this.name, PlaylistSituation.PERMANENT);
        this.lasSongIndex = 0;
        this.fileIndex = 0;
        this.name = name;
    }

    public Song getLastSong() {
        return this.lastSong;
    }

    public void setLastSong() {
        this.lastSong = (Song)this.sharedPlayList.getSongs().get(this.lasSongIndex);
    }

    public void setLasSongIndex(int lasSongIndex) {
        this.lasSongIndex = lasSongIndex;
    }

    public String getName() {
        return this.name;
    }

    public PlayList getSharedPlayList() {
        return this.sharedPlayList;
    }

    public void addSongToSharedPlayList(Song song) {
        this.sharedPlayList.addSong(song);
    }

    public String getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
