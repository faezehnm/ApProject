package playControl;

import javazoom.jl.decoder.JavaLayerException;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import other.Song;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Hashtable;
import static java.lang.Thread.sleep;


public class PlaySlider extends JPanel implements ChangeListener {
    private JSlider jSlider ;
    private int duration=0;
    Hashtable hashtable = new Hashtable();
    private JLabel startLable = new JLabel("0:00") ;
    private JLabel finishLable = new JLabel("0:00") ;
    private boolean repeat ;
    private boolean paused = false ;
    private boolean stop = false ;
    private int pausedTime = 0;
    MusicPlayer player ;
    Song song;


    public PlaySlider(MusicPlayer player) throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException
    {

        this.player= player ;

        jSlider= new JSlider();
        jSlider.addChangeListener(this);
        jSlider.setValue(0);

        hashtable.put( 0 , startLable );
        hashtable.put( jSlider.getMaximum() , finishLable);

        jSlider.setLabelTable(hashtable);
        jSlider.setPaintLabels(true);
        jSlider.setPreferredSize(new Dimension(600,32));
        jSlider.setBackground(Color.white);

        add(jSlider);
        setBackground(Color.white);

    }

    public void setSong(Song song){
        this.song = song ;
    }

    public void play()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try {
                    updateSlider();
                } catch (ReadOnlyFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TagException e) {
                    e.printStackTrace();
                } catch (InvalidAudioFrameException e) {
                    e.printStackTrace();
                } catch (CannotReadException e) {
                    e.printStackTrace();
                }
                for (int i = pausedTime; i <= duration; i++) {

                    if(paused || stop )
                        stop();

                    if(!paused && !stop ) {
                        manageLableText(i);

                        if((jSlider.getValue()-i>1 || i-jSlider.getValue()>1)&& player.isCompeletIn()==false ) {
                            try {
                                player.skip(duration,jSlider.getValue());
                                i = jSlider.getValue();

                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JavaLayerException e) {
                                e.printStackTrace();
                            }
                        }

                        jSlider.setValue(i);

                        try {
                            sleep(1000);
                            if (i == duration && repeat)
                                actionToRepeat();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        }.start();

    }

    public void stopForAnotherSong(){

        setStop(true);
        pausedTime=0 ;

    }

    private void actionToRepeat(){
        pausedTime = 0;
        jSlider.setValue(0);
        play();
    }

    public void resume()
    {
        setPaused(false);
        play();
    }

    public void pause()
    {
        pausedTime = jSlider.getValue();
        setPaused(true);
    }

    private String getTimeInMinute(int duration)
    {
        String result ;
        int min ;
        int sec ;
        min = duration/60 ;
        sec = duration-(min*60);
        result = min+":"+sec ;
        return result ;

    }

    public void setRepeat(boolean repeat)
    {
        this.repeat = repeat;
    }

    public void setPaused( boolean paused)
    {
        this.paused= paused ;
    }

    public void setStop(boolean stop)
    {
        this.stop = stop ;
    }

    public void actionAfterStop(){
        pausedTime=0;
        jSlider.setValue(0);
        setStop(false);
    }

    public int getTime() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException
    {

        File file=new File(song.getFileAddress());
        AudioFile audioFile = AudioFileIO.read(file);
        duration= audioFile.getAudioHeader().getTrackLength();
        return duration;
    }

    private void manageLableText(int i){
        if (i < 60) {
            if (i < 10)
                startLable.setText("0:0" + i);
            else
                startLable.setText("0:" + i);
        }
        else if (i >= 60) {
            int min = i / 60;
            int sc = i - (min * 60);
            if (sc < 10)
                startLable.setText("" + min + ":0" + sc);
            else
                startLable.setText("" + min + ":" + sc);
        }
    }

    private void updateSlider() throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {
        duration= getTime();
        jSlider.setMaximum(duration);
        finishLable.setText(getTimeInMinute(duration));
        hashtable.clear();
        hashtable.put( 0 , startLable );
        hashtable.put( jSlider.getMaximum() , finishLable);
    }

    @Override
    public void stateChanged(ChangeEvent e) { }


}
