package playControl;

import java.io.*;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {

    private FileInputStream is;
    private Player player;
    private boolean repeat=false;
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private String musicFilePath;

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

    public void skip(int duration , int currentTime) throws URISyntaxException, IOException, JavaLayerException
    {
        pause();

        pauseLocation=getLengthInStream(duration,currentTime);

        resume();
    }

    public Long getLengthInStream(int duration,int currentTime)
    {

      pauseLocation= totalSongLength-((totalSongLength/duration)*currentTime);
      return pauseLocation ;

    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean getRepeat() {
        return repeat;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isCompeletIn(){
        return player.isComplete();
    }

}