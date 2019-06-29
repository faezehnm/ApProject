package Network;

import java.io.*;

/**
 *  when user is listening to a song which exist in her sharedPlaylist send it to all her friends
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class SendLastSong {
    /**
     * creat an object of SendLastSong
     * @param user owner of JPotify
     * @throws IOException
     */
    public SendLastSong(User user) throws IOException
    {
        if(user.getSharedPlaylist() != null) {
            for (int i = 0; i < user.getSharedPlaylist().getSongs().size(); i++) {
                if(user.getLastSong() != null ) {
                    if (user.getLastSong().getFileAddress().equals(user.getSharedPlaylist().getSongs().get(i).getFileAddress())) {
                        System.out.println("yeap it is in sharePlayList");
                        user.setLasSongIndex(i);

                        ForServer forServer = new ForServer(11,user);
                        Network network = new Network(forServer);
                        new Thread(network).start();

                        break;
                    }
                }
            }
        }


    }
}
