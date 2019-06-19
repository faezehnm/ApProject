package FirendsActivity;

import java.io.Serializable;

public class ForServer implements Serializable {
    private int type;
    private Friend friend ;

    public ForServer(int type, Friend friend ) {
        this.type = type;
        this.friend= friend ;
    }

    public int getType() {
        return type;
    }

    public Friend getFriend() {
        return friend;
    }

}



