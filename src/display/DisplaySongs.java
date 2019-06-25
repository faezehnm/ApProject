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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static display.DisplaySongsSituation.PLAYING;

/**
 * This class is used for displaying songs(All songs or a group of songs(songs of an albume or playlist))
 * in the center part of the GUI to play them or select and add some of them to a new playlist.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class DisplaySongs extends DisplaySongsGroup {

    private DisplaySongsSituation displaySongsSituation;
    private PlayList playList;
    private String situation;
    private boolean playing;
    private DisplayListsControl displayListsControl;

    /**
     * crates an object of this class.
     * @param songArrayList an arraylist of songs to be displayed.
     * @param playMusicGUI is an object of PlayMusic GUI which is added to the south of main frame
     * @param playList is a playlist for times that we are working on a playlist and is null in other times.
     * @param displayListsControl is an object of DisplayListsControl class that is controling the left panel
     * @param displaySongsSituation shows if displaying is for playing songs or adding some songs to playlist.
     * @throws Exception
     */

    public DisplaySongs(ArrayList<Song> songArrayList , PlayMusicGUI playMusicGUI , PlayList playList , DisplayListsControl displayListsControl , DisplaySongsSituation displaySongsSituation , JPotifyGUI mainGUI) throws Exception{
        super(songArrayList , playMusicGUI , mainGUI);
        this.playing = playing;
        this.playList = playList;
        this.displayListsControl = displayListsControl;
        this.displaySongsSituation = displaySongsSituation;
    }

    /**
     * Adds actionlistener and mouselistener to button according to the display situation(selection or playing)
     * @param btn is the song button
     * @param music is a music from song kind
     */

    @Override
    protected void addActionListeners(JButton btn, Music music , JPanel pnl){
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
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3){
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem addToFavorite = new JMenuItem("add to favorite songs");
                    JMenuItem addToShared = new JMenuItem("add to shared playlist");
                    JMenuItem delet = new JMenuItem("delet");
                    popupMenu.add(addToFavorite);
                    popupMenu.add(addToShared);
                    popupMenu.add(delet);
                    popupMenu.show(btn , e.getX() , e.getY());
                    addToFavorite.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            displayListsControl.addSongToPlaylist(song , "Favorite Songs");
                        }
                    });
                    addToShared.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            displayListsControl.addSongToPlaylist(song , "Shared Playlist");
                        }
                    });
                    delet.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            musics.remove(song);
                            panel.remove(pnl);
                            if(musics.equals(displayListsControl.getSongs())){
                                if(song.getAlbume().getSongs().size() == 1){
                                    displayListsControl.deletAlbume(song.getAlbume());
                                }
                                else{
                                    song.getAlbume().deletSong(song);
                                }
                                for(PlayList p : displayListsControl.getPlaylists()){
                                    if(p.getSongs().contains(song)){
                                        p.removeSong(song);
                                    }
                                }
                                displayListsControl.setDisplaySongs(displayListsControl.getSongs());
                            }
                            else{
                                displayListsControl.setDisplayPlaylist(playList , playMusicGUI);
                            }
                        }
                    });
                }
            }
        });
    }
}
