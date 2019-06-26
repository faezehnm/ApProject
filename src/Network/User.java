package Network;

import FirendsActivity.Friend;
import music.PlayList;
import music.Song;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name ;
    private String password ;
    private ArrayList<Friend> friends = new ArrayList<>();
    private File file;
    private PlayList sharedPlaylist ;
    private Song lastSong ;
    private int lasSongIndex  = 0;

    public int getLasSongIndex() {
        return lasSongIndex;
    }

    public void setLasSongIndex(int lasSongIndex) {
        this.lasSongIndex = lasSongIndex;
    }

    public PlayList getSharedPlaylist() {
        return sharedPlaylist;
    }

    public void setSharedPlaylist(PlayList sharedPlaylist) {
        this.sharedPlaylist = sharedPlaylist;
    }

    public Song getLastSong() {
        return lastSong;
    }

    public void setLastSong()
    {
        lastSong = getSharedPlaylist().getSongs().get(lasSongIndex);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    /*
    shared playlist and last song
     */

    public User(String name, String password ){
        this.name = name ;
        this.password = password ;
    }

    public void addFriend(Friend friend){
        friends.add(friend);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }
}
