package Network;

import FirendsActivity.Friend;
import music.PlayList;
import music.Song;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class User implements Serializable{

    private String name ;
    private String password ;
    private ArrayList<Friend> friends = new ArrayList<>();
    private Friend currentFriend ;
    private PlayList sharedPlaylist ;
    private Song lastSong ;
    private String lastTime ;
    private int lasSongIndex  = 0;

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

    private void timerOfLastSong()
    {
        /*
        have a timer in 5 minute to set last time playing
        if set last song during 5 minutes didn't change
         */
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
