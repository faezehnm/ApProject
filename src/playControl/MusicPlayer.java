package playControl;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 *  MusicPlayer provides a variety of features such as play,pause,resume,skip,stop & repeat music
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
public class MusicPlayer{

    private FileInputStream is;
    private Player player;
    private boolean repeat = false;
    private boolean shuffle = false ;
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private String musicFilePath;

    /**
     * play a song
     * @param musicFilePath song's path that we want to play
     * @throws FileNotFoundException if file of song not find
     * @throws JavaLayerException all exceptions depend on jlayer
     * @throws IOException for song's file
     * @throws URISyntaxException for musicPlayer
     */
    public void play( String musicFilePath ) throws FileNotFoundException, JavaLayerException, IOException, URISyntaxException
    {

        this.musicFilePath = musicFilePath;

        is = new FileInputStream(musicFilePath);

        totalSongLength =  is.available();

        player = new Player( is );


        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    player.play();

                    if( player.isComplete() && repeat )
                    {
                        play( musicFilePath );
                    }


                }
                catch ( JavaLayerException | IOException ex)
                {
                    System.err.println("::: there was an error to play " + musicFilePath );
                } catch (URISyntaxException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    /**
     * resume a paused song
     * @throws FileNotFoundException if file of song not find
     * @throws JavaLayerException all exceptions depend on jlayer
     * @throws IOException
     * @throws URISyntaxException
     */
    public void resume( ) throws FileNotFoundException, JavaLayerException, IOException, URISyntaxException
    {

        paused = false;

        is = new FileInputStream(musicFilePath);

        is.skip( totalSongLength - pauseLocation );

        player = new Player( is );

        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    player.play();
                    if( player.isComplete() && repeat )
                    {
                        play( musicFilePath );
                    }
                }
                catch (JavaLayerException | IOException | URISyntaxException ex)
                {
                    System.err.println("::: there was an error to play " + musicFilePath );
                }
            }
        }.start();

    }

    /**
     * close player and stop playing music
     */
    public void stop()
    {
        paused = false;

        if( null != player)
        {
            player.close();

            totalSongLength = 0;
            pauseLocation = 0;
        }

    }

    /**
     * pause music
     */
    public void pause()
    {

        paused = true;
        if( null != player)
        {
            try
            {
                pauseLocation = is.available();
                player.close();
            }
            catch (IOException ex)
            {
                System.out.println("::: error when song is paused");
            }

        }

    }

    /**
     * skip song when situtaion of playSlider's knob change.
     * @param duration durattion of song
     * @param currentTime knob situation after skip
     * @throws URISyntaxException
     * @throws IOException
     * @throws JavaLayerException( all exceptions depend on jlayer)
     */
    public void skip(int duration , int currentTime) throws URISyntaxException, IOException, JavaLayerException
    {
        pause();

        pauseLocation=getLengthInStream(duration,currentTime);

        resume();
    }

    /**
     * change long of skip from second into byte for inputStream
     * @param duration durattion of song
     * @param currentTime knob situation after skip
     * @return
     */
    public Long getLengthInStream(int duration,int currentTime)
    {

        pauseLocation= totalSongLength-((totalSongLength/duration)*currentTime);
        return pauseLocation ;

    }

    /**
     * if reapet button listen
     * @return a boolean that show is in repeat state or not
     */
    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean getRepeat() {
        return repeat;
    }

    public boolean getShuffle(){
        return shuffle ;
    }

    public void setShuffle(boolean shuffle){
        this.shuffle = shuffle ;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * if player complet
     * @return boolean from a method in Player class that show player is compelete or not
     */
    public boolean isCompeletIn(){
        return player.isComplete();
    }

}