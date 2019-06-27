package Network;

import FirendsActivity.Friend;
import music.PlayList;
import music.Song;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class User {
    private String name ;
    private String password ;
    private ArrayList<Friend> friends = new ArrayList<>();
    private File file;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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



}
