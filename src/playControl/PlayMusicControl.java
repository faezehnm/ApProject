package playControl;
import display.DisplaySongsGroup;
import javazoom.jl.decoder.JavaLayerException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import music.Song;
import sun.management.counter.perf.PerfLongArrayCounter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlayMusicControl implements ActionListener {

    private PlayMusicGUI playMusicGUI ;

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


    public PlayMusicControl(JButton btnPlay , JButton btnNext , JButton btnPrevious , JButton btnRepeat , JButton btnShuffle , ImageIcon imPause , ImageIcon imPlay , ImageIcon imRepeat , ImageIcon imRepeat1, PlaySlider playSlider, MusicPlayer player ,PlayMusicGUI playMusicGUI ,ImageIcon imShuffle ,ImageIcon imShuffle1) throws Exception {

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


        btnShuffle.addActionListener(this);
        btnPrevious.addActionListener(this);
        btnPlay.addActionListener(this);
        btnNext.addActionListener(this);
        btnRepeat.addActionListener(this);


    }

    public void setSong(Song song ) {
        this.song = song ;
        actionToPlayerAnotherSong();
        actionToPlaySliderAnotherSong();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==btnShuffle){
            actionToButtonShuffle();
        /*
        :)
         */
        }
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

    private void actionToButtonPlay()
    {

        if (!isItPlaying) {
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
            getPlayButton().setIcon(imPlay);
            player.pause();
            playSlider.pause();
            isItPlaying= false ;
        }

    }

    private void actionToButtonPlayAnotherSong()
    {
        setchangeSong(false);
        isItPlaying = false ;
        firstTime = true ;
        playSlider.actionAfterStop();
        actionToButtonPlay();
    }

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

    private void actionToButtonNext()
    {
        try {
            playMusicGUI.setSong(DisplaySongsGroup.returnNext(song));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void ctionToButtonPrevious()
    {
        try {
            playMusicGUI.setSong(DisplaySongsGroup.returnPrevious(song));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void actionToPlayerAnotherSong()
    {
        player.stop();
        setchangeSong(true);
    }

    private void actionToPlaySliderAnotherSong()
    {

        playSlider.setSong(song);
        playSlider.stopForAnotherSong();
        playSlider.setPaused(false);

    }

    public void setchangeSong(boolean nextSong)
    {
        this.changeSong = nextSong;
    }
}
