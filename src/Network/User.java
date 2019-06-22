package Network;

import java.util.ArrayList;

public class User {
    private String name ;
    private String password ;
    private UserState userState ;
    static ArrayList<User> users = new ArrayList<User>();
    /*
    shared playlist and last song
     */

    public User(String name, String password , UserState userState){
        this.name = name ;
        this.password = password ;
        this.userState = userState ;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser( User user){
        users.add(user);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserState getUserState() {
        return userState;
    }


}
