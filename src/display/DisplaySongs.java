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

    public DisplaySongs(ArrayList<Song> songArrayList , PlayMusicGUI playMusicGUI , boolean playing , PlayList playList , DisplayListsControl displayListsControl) throws Exception{
        super(songArrayList , playMusicGUI);
        this.playing = playing;
        //System.out.println(this.situation);
        //System.out.println(situation);
        this.playList = playList;
        this.displayListsControl = displayListsControl;
    }


    @Override
    protected void addActionListeners(JButton btn, Music music){
        Song song = (Song) music;
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playing){
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
