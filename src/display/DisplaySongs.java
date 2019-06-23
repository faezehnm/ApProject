package display;

import home.JPotifyGUI;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import music.Song;
import playControl.PlayMusicGUI;
import music.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static display.DisplaySongsSituation.PLAYING;

public class DisplaySongs extends DisplaySongsGroup {

    private DisplaySongsSituation displaySongsSituation;
    private PlayList playList;
    private String situation;
    private boolean playing;
    private DisplayListsControl displayListsControl;

    public DisplaySongs(ArrayList<Song> songArrayList , PlayMusicGUI playMusicGUI , PlayList playList , DisplayListsControl displayListsControl , DisplaySongsSituation displaySongsSituation) throws Exception{
        super(songArrayList , playMusicGUI);
        this.playing = playing;
        this.playList = playList;
        this.displayListsControl = displayListsControl;
        this.displaySongsSituation = displaySongsSituation;
    }


    @Override
    protected void addActionListeners(JButton btn, Music music){
        Song song = (Song) music;
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(displaySongsSituation.equals(PLAYING)){
                    try {
                        playMusicGUI.setSong(song);
                        displayListsControl.replace(song);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    playList.addSong(song);
                }
            }
        });
    }
}
