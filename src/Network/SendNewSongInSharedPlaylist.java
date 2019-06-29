package Network;

import home.JPotifyGUI;
import music.Song;

import java.io.IOException;

/**
 *  when user add a song to her sharedPlaylist , send it for friend
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class SendNewSongInSharedPlaylist {

    private JPotifyGUI jPotifyGUI ;
    private ForServer forServer;

    /**
     * cerat SendNewSongInSharedPlaylist
     * @param song a song which add to sharedPlaylist
     * @param jPotifyGUI jPotifyGUI
     * @throws IOException
     */
    public SendNewSongInSharedPlaylist(Song song ,JPotifyGUI jPotifyGUI) throws IOException
    {

        this.jPotifyGUI = jPotifyGUI ;
        song.toByteArray();
        jPotifyGUI.getUser().setNewSongInSharedPlaylist(song);

        forServer = new ForServer(13,jPotifyGUI.getUser());
        Network network = new Network(forServer);
        new Thread(network).start();

    }
}
