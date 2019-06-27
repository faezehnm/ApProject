package playControl;
import display.DisplaySongsGroup;
import home.JPotifyGUI;
import javazoom.jl.decoder.JavaLayerException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import music.Song;
import sun.management.counter.perf.PerfLongArrayCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;

/**
 * PlayMusicControl controll all components that use in PlayMusicGUI
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class PlayMusicControl implements ActionListener,Runnable {

    private PlayMusicGUI playMusicGUI ;
    private GraphicEqualizerPanel graphicEqualizerPanel;
    private JPanel leftPanel;
    private JPanel junk;
    private JButton btnPlay ;
    private JButton btnNext ;
    private JButton btnPrevious ;
    private JButton btnRepeat ;
    private JButton btnShuffle ;
    private ImageIcon imPause ;
    private ImageIcon imPlay ;
    private ImageIcon imRepeat ;
    private ImageIcon imRepeat1 ;
    private ImageIcon imShuffle ;
    private ImageIcon imShuffle1 ;


    private MusicPlayer player = new MusicPlayer();
    private PlaySlider playSlider = new PlaySlider(player);
    private Song song ;

    private int pausedOnFrame = 0;
    private boolean isItPlaying=false;
    private boolean firstTime=true;
    private boolean changeSong = false ;
    public static JPotifyGUI jPotifyGUI;

    /**
     * creat PlayMusicControl
     * @param btnPlay for play and pause music
     * @param btnNext for play next music
     * @param btnPrevious for play previous music
     * @param btnRepeat for repeat current music
     * @param btnShuffle for play a random music
     * @param imPause image of btnPlay when music is paused
     * @param imPlay image of btnPlay when music is resumed
     * @param imRepeat image of btnPlay when a song is repeating
     * @param imRepeat1 image of btnPlay when a song is not repeating
     * @param playSlider slider to show music progress when playing
     * @param player provids play,pause,stop,skip & resume music
     * @param playMusicGUI GUI of all component that you see in south of JPotify app
     * @param imShuffle image of btnPlay when no shuffle
     * @param imShuffle1 image of btnPlay when shuffle
     * @throws Exception if player is null
     */
    public PlayMusicControl(JButton btnPlay , JButton btnNext , JButton btnPrevious , JButton btnRepeat , JButton btnShuffle , ImageIcon imPause , ImageIcon imPlay , ImageIcon imRepeat , ImageIcon imRepeat1, PlaySlider playSlider, MusicPlayer player , PlayMusicGUI playMusicGUI , ImageIcon imShuffle , ImageIcon imShuffle1, JPotifyGUI jPotifyGUI , GraphicEqualizerPanel graphicEqualizerPanel , JPanel leftPanel , JPanel junk) throws Exception
    {
        this.graphicEqualizerPanel = graphicEqualizerPanel;
        this.jPotifyGUI =jPotifyGUI ;
        this.playMusicGUI= playMusicGUI ;
        this.btnNext=btnNext ;
        this.btnPlay=btnPlay ;
        this.btnPrevious=btnPrevious;
        this.btnRepeat= btnRepeat ;
        this.btnShuffle=btnShuffle ;
        this.imPause=imPause ;
        this.imPlay = imPlay ;
        this.imRepeat= imRepeat ;
        this.imRepeat1 = imRepeat1 ;
        this.imShuffle= imShuffle ;
        this.imShuffle1 = imShuffle1 ;
        this.playSlider=playSlider ;
        this.player=player ;
        this.junk = junk;


        btnShuffle.addActionListener(this);
        btnPrevious.addActionListener(this);
        btnPlay.addActionListener(this);
        btnNext.addActionListener(this);
        btnRepeat.addActionListener(this);


    }

    /**
     * set song( use when current song change)
     * @param song a song that is playing
     */
    public void setSong(Song song )
    {
        this.song = song ;
        actionToPlayerAnotherSong();
        actionToPlaySliderAnotherSong();
        //actionToButtonPlayAnotherSong();
    }

    /**
     * handle action listener of all buttons
     * @param e click on button
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()==btnShuffle)
            actionToButtonShuffle();

        if( e.getSource()==btnPrevious)
            ctionToButtonPrevious();

        if( e.getSource()==btnPlay)
        {
            if(!changeSong)
                actionToButtonPlay();

            if(changeSong)
                actionToButtonPlayAnotherSong();
        }

        if( e.getSource()==btnNext)
           actionToButtonNext();

        if( e.getSource()==btnRepeat)
            actionToButtonRepeat();
    }

    public JButton getPlayButton()
    {
        return btnPlay;
    }

    public JButton getRepeatButton()
    {
        return btnRepeat;
    }

    public JButton getBtnShuffle()
    {
        return btnShuffle;
    }

    /**
     * all actions that should happen when click on play button
     */
    private void actionToButtonPlay()
    {

        if (!isItPlaying) {
            playEqualizer();
            getPlayButton().setIcon(imPause);
            isItPlaying=true ;
            if( firstTime ) {
                firstTime=false ;
                try {
                    player.play(song.getFileAddress());
                    playSlider.play();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (JavaLayerException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                } catch (URISyntaxException e4) {
                    e4.printStackTrace();
                }
            }
            else{
                try {
                    player.resume();
                    playSlider.resume();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (JavaLayerException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                } catch (URISyntaxException e4) {
                    e4.printStackTrace();
                }

            }

        } else {
            stopEqualizer();
            getPlayButton().setIcon(imPlay);
            player.pause();
            playSlider.pause();
            isItPlaying= false ;
        }
        new Thread(this).start();

    }

    /**
     * all actions that should happen when click on play button and music is changed
     */
    private void actionToButtonPlayAnotherSong()
    {

      //  setLastSong(this.song);
        setchangeSong(false);
        isItPlaying = false ;
        firstTime = true ;
        playSlider.actionAfterStop();
        actionToButtonPlay();
    }

    /**
     * all actions that should happen when click on repeat button
     */
    private void actionToButtonRepeat()
    {

        if( player.getRepeat()==false) {
            player.setRepeat(true);
            playSlider.setRepeat(true);
            getRepeatButton().setIcon(imRepeat);
        }
        else if(player.getRepeat()== true){
            player.setRepeat(false);
            playSlider.setRepeat(false);
            getRepeatButton().setIcon(imRepeat1);
        }
    }

    /**
     * all actions that should happen when click on shuffle button
     */
    private void actionToButtonShuffle()
    {
        if( player.getShuffle()== false ){
            player.setShuffle(true);
            getBtnShuffle().setIcon(imShuffle1);
            try {
                playMusicGUI.setSong(DisplaySongsGroup.returnShuffle(song));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if( player.getShuffle()== true ){
            player.setShuffle(false);
            getBtnShuffle().setIcon(imShuffle);
        }
    }

    /**
     * all actions that should happen when click on next button
     */
    private void actionToButtonNext()
    {
        try {
            playMusicGUI.setSong(DisplaySongsGroup.returnNext(song));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * all actions that should happen when click on Previous button
     */
    private void ctionToButtonPrevious()
    {
        try {
            playMusicGUI.setSong(DisplaySongsGroup.returnPrevious(song));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * all actions which refrence to player that should happen when click on play button and song is changed
     */
    private void actionToPlayerAnotherSong()
    {
        player.stop();
        setchangeSong(true);
    }

    /**
     * all actions which refrence to playeSlider and should happen when click on play button and song is changed
     */
    private void actionToPlaySliderAnotherSong()
    {

        playSlider.setSong(song);
        playSlider.stopForAnotherSong();
        playSlider.setPaused(false);

    }

    /**
     * set when song changed
     * @param nextSong a boolean that show a song is changed
     */
    public void setchangeSong(boolean nextSong)
    {
        this.changeSong = nextSong;
    }

    /**
     * if this song exists in user's sharedPlayList
     * @param song current music that is playing
     */
    private void setLastSong(Song song)
    {
        for( int i=0 ; i<jPotifyGUI.getUser().getSharedPlaylist().getSongs().size() ; i++){
            if(song.getFileAddress().equals(jPotifyGUI.getUser().getSharedPlaylist().getSongs().get(i).getAlbumeName()) ){
                jPotifyGUI.getUser().setLasSongIndex(i);
                jPotifyGUI.getUser().setLastSong();
                jPotifyGUI.getUser().setLastTime("0");
                break;
            }
        }
    }

    private void notOnRepeat(){
        while (true) {
            if (player.getPlayer().isComplete() && player.getRepeat() == false ) {
                actionToButtonNext();
                break ;
            }
            else if( player.getPlayer().isComplete() && player.getShuffle() == true && player.getRepeat()==false){
                try {
                    playMusicGUI.setSong(DisplaySongsGroup.returnShuffle(song));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void run() {
        notOnRepeat();
    }

    private void playEqualizer(){
        junk.setLayout(new BorderLayout());
        junk.add(graphicEqualizerPanel , BorderLayout.CENTER);
        jPotifyGUI.revalidate();
        jPotifyGUI.repaint();
    }

    private void stopEqualizer(){
        junk.remove(graphicEqualizerPanel);
        jPotifyGUI.revalidate();
        jPotifyGUI.repaint();
    }
}
