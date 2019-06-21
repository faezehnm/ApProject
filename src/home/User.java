package home;

import java.util.ArrayList;

public class User {
    private String name ;
    private String password ;
    static ArrayList<User> users = new ArrayList<User>();

    public User(String name, String password){
        this.name = name ;
        this.password = password ;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
