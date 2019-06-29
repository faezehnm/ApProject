package FirendsActivity;

import java.io.Serializable;
import music.PlayList;
import music.PlaylistSituation;
import music.Song;
/**
 *  each friend inforamtion that should show in FriendActivityGUI
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class Friend implements Serializable {

    private String name;
    private PlayList sharedPlayList;
    private Song lastSong;
    private String lastTime;
    private int lasSongIndex;
    private int fileIndex;


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

    /**
     * with index of lastFile find last song of friend which exist in her sharedPlaylist
     */
    public void setLastSong()
    {
        System.out.println(getSharedPlayList().getSongs().size());
        if( getSharedPlayList() != null && sharedPlayList.getSongs().size()!=0 ) {
            this.lastSong = (Song) this.sharedPlayList.getSongs().get(this.lasSongIndex);
        }
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

    /**
     * add a song to friedn sharedPlaylist
     * @param song a song which should add to friend playList
     */
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
