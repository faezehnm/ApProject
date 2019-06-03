import javazoom.jl.decoder.JavaLayerException;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Hashtable;
import static java.lang.Thread.sleep;


public class PlaySlider extends JPanel implements ChangeListener {
    private JSlider jSlider ;
    private int duration=0;
    private String time ;
    private JLabel startLable = new JLabel("0:0") ;
    private JLabel finishLable = new JLabel(":)") ;
    private boolean repeat ;
    private boolean paused = false ;
    private int pausedTime = 0;


    public PlaySlider() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException, CannotReadException, InvalidAudioFrameException {

        duration=getTime();

        jSlider= new JSlider();
        jSlider.addChangeListener(this);
        jSlider.setValue(0);
        jSlider.setMaximum(duration);
        Hashtable hashtable = new Hashtable();
        hashtable.put( new Integer( 0 ), startLable );
        time = getTimeInMinute(duration);
        finishLable.setText(time);
        hashtable.put( new Integer( jSlider.getMaximum() ), finishLable);
        jSlider.setLabelTable(hashtable);
        jSlider.setPaintLabels(true);
        add(jSlider);


    }

    public void play(){
        new Thread()
        {
            @Override
            public void run()
            {
                for (int i = pausedTime; i <= duration; i++) {
                    if(paused)
                        stop();
                    //System.out.println(jSlider.getValue()+","+i);
                    if(jSlider.getValue()-i>1 || i-jSlider.getValue()>1)
                        i=jSlider.getValue();

                    jSlider.setValue(i);

                    if(!paused) {
                        if (i < 60)
                            if( i<10)
                                startLable.setText("0:0" + i);
                            else
                                startLable.setText("0:" + i);
                        else if (i >= 60) {
                            int min = i / 60;
                            int sc = i - (min * 60);
                            if(sc<10)
                                startLable.setText("" + min + ":0" + sc);
                            else
                                startLable.setText("" + min + ":" + sc);
                        }
                        try {
                            sleep(1000);
                            if (i == duration && repeat) {
                                play();
                                pausedTime = 0;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

    }

    public void resume(){
        setPaused(false);
        play();
    }

    public void pause(){
        pausedTime = jSlider.getValue();
        setPaused(true);
    }

    private String getTimeInMinute(int duration){
        String result ;
        int min ;
        int sec ;
        min = duration/60 ;
        sec = duration-(min*60);
        result = min+":"+sec ;
        return result ;

    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void setPaused( boolean paused) {
        this.paused= paused ;
    }

    public int getTime() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        File file=new File("src/music/Happier.mp3");
        AudioFile audioFile = AudioFileIO.read(file);
        duration= audioFile.getAudioHeader().getTrackLength();
        return duration;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
       // System.out.println(jSlider.getValue());
//        if( e.getSource()==jSlider && paused==tr) {
//            System.out.println("****");
            //pause();
//           duration = jSlider.getValue();
//        resume();
        //}
    }


}
