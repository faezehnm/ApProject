package Network;

import FirendsActivity.Friend;
import music.PlayList;
import music.Song;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
/**
 *  user of JPotify
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class User implements Serializable{

    private String name ;
    private String password ;
    private ArrayList<Friend> friends = new ArrayList<>();
    private Friend currentFriend ;
    private PlayList sharedPlaylist ;
    private Song lastSong  = null ;
    private String lastTime ;
    private int lasSongIndex  ;
    private boolean noLastSong = true;
    /**
     * creat a user
     * @param name user name
     * @param password user password
     */
    public User(String name, String password )
    {
        this.name = name ;
        this.password = password ;
    }

    public int getLasSongIndex() {
        return lasSongIndex;
    }

    public void setLasSongIndex(int lasSongIndex) {
        this.lasSongIndex = lasSongIndex;
        noLastSong = false ;
    }

    public boolean isNoLastSong() {
        return noLastSong;
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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void removeFriend(Friend friend){
        friends.remove(friend);
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public void addFriend(Friend friend){
        friends.add(friend);
    }

    public Friend getCurrentFriend() {
        return currentFriend;
    }

    public void setCurrentFriend(Friend currentFriend) {
        this.currentFriend = currentFriend;
    }
    public void setLastSong(Song song){
        this.lastSong = song;
    }

}
