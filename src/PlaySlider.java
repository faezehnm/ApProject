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
    private JLabel startLable = new JLabel("0") ;
    private JLabel finishLable = new JLabel(":)") ;
    private boolean repeat ;
    private boolean paused = false ;
    private int pausedTime ;


    public PlaySlider() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException, CannotReadException, InvalidAudioFrameException {

        File file=new File("src/music/Happier.mp3");
        AudioFile audioFile = AudioFileIO.read(file);
        duration= audioFile.getAudioHeader().getTrackLength();

        jSlider= new JSlider();
        jSlider.addChangeListener(this);
        jSlider.setMaximum(duration);
        Hashtable hashtable = new Hashtable();
        hashtable.put( new Integer( 0 ), startLable );
        time = getTimeInMinute(duration);
        finishLable.setText(time);
        hashtable.put( new Integer( jSlider.getMaximum() ), finishLable);
        jSlider.setLabelTable(hashtable);
        jSlider.setPaintLabels(true);
        add(jSlider);
        jSlider.setValue(0);



    }

    public void play(){
        new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i <= duration; i++) {
                    jSlider.setValue(i);
                    startLable.setText("" + i);
                    try {
                        sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

    }

    public void Pause(){
        pausedTime = jSlider.getValue();
        setPaused();
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

    public void setPaused() {
        this.paused= true ;
    }


    @Override
    public void stateChanged(ChangeEvent e) {

    }


}
