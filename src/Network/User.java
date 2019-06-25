package Network;

import FirendsActivity.Friend;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name ;
    private String password ;
    private ArrayList<Friend> friends = new ArrayList<>();

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
