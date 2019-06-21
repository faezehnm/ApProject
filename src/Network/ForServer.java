package Network;

import java.io.Serializable;

public class ForServer implements Serializable {
    private int type;
    private User user;

    public ForServer(int type, User user) {
        this.type = type;
        this.user = user ;
    }

    public int getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

}
