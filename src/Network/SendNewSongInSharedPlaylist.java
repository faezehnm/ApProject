package Network;

import home.JPotifyGUI;
import music.Song;

import java.io.IOException;


public class SendNewSongInSharedPlaylist {

    private JPotifyGUI jPotifyGUI ;
    private ForServer forServer;

    public SendNewSongInSharedPlaylist(Song song ,JPotifyGUI jPotifyGUI) throws IOException {

        this.jPotifyGUI = jPotifyGUI ;
        song.toByteArray();
        jPotifyGUI.getUser().setNewSongInSharedPlaylist(song);

        forServer = new ForServer(13,jPotifyGUI.getUser());
        Network network = new Network(forServer);
        new Thread(network).start();

    }
}
