package Network;

import java.io.Serializable;
/**
 *  transfer message between client & server with objects of this class
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class ForServer implements Serializable {

    private int type;
    private User user;

    /**
     * creat ForServer
     * @param type message type which define message
     * @param user user who sends message to server
     */
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
