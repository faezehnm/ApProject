import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.audio.AudioStream;

import java.io.IOException;

public class Main {
    public static JPotifyGUI jPotifyGUI ;

    public static void main(String[] args) throws Exception {
        JPotifyGUI jPotifyGUI = new JPotifyGUI();
        Song s = new Song("src/Mehdi Yarrahi - Akharin Asir 128(UpMusic).mp3");
        System.out.println(s.getMusicName());
    }
}

