import Welcome.WelcomeGUI;
import home.JPotifyGUI;
import music.Albume;
import music.PlayList;
import music.Song;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("src/jpotify.bin");
        if (file.exists()){
            //System.out.println(000);
            JPotifyGUI jPotifyGUI = new JPotifyGUI();
            FileInputStream fileIn = new FileInputStream(file.getPath());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            int lastSongExists = in.readInt();
            //System.out.println(i);
            if(lastSongExists == 1){
                Song s = (Song) in.readObject();
                jPotifyGUI.getPlayMusicGUI().setSong(s);
            }
            int numberOfSongs = in.readInt();
            //System.out.println(numberOfSongs);
            for(int i = 0 ; i < numberOfSongs ; i++){
                Song s = (Song) in.readObject();
                jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addSong(s);
            }
            int numberOfAlbumes = in.readInt();
            for(int i = 0 ; i < numberOfAlbumes ; i++){
                Albume a = (Albume) in.readObject();
                jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addAlbum(a);
            }
            int numberOfPlaylists = in.readInt();
            //System.out.println(numberOfPlaylists + "ooooooooooo");
            for(int i = 0 ; i < numberOfPlaylists ; i++){
                PlayList p = (PlayList) in.readObject();
                jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addPlaylist(p);
            }
            in.close();
        }
        else {
            JPotifyGUI jPotifyGUI = new JPotifyGUI();
        }
       // WelcomeGUI welcomeGUI  = new WelcomeGUI();
    }
}

