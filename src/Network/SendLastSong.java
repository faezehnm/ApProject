package Network;

import java.io.*;

public class SendLastSong {
    int result=0 ;
    public SendLastSong(User user) throws IOException
    {

        for( int i=0 ; i< user.getSharedPlaylist().getSongs().size() ; i++ ){
            if( user.getLastSong().getFileAddress().equals(user.getSharedPlaylist().getSongs().get(i))) {
                result = i;
                user.setLasSongIndex(result);
                break;
            }
        }

        ForServer forServer = new ForServer(11,user);
        Network network = new Network(forServer);
        new Thread(network).start();
        result = 0;

    }
}
