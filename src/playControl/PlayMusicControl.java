package playControl;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlayMusicControl implements ActionListener {
    private JButton btnPlay ;
    private JButton btnNext ;
    private JButton btnPrevious ;
    private JButton btnRepeat ;
    private JButton btnShuffle ;
    MusicPlayer player = new MusicPlayer();
    PlaySlider playSlider = new PlaySlider(player);
    private int pausedOnFrame = 0;
    private boolean isItPlaying=false;
    private boolean firstTime=true;
    private ImageIcon imPause ;
    private ImageIcon imPlay ;
    private ImageIcon imRepeat ;
    private ImageIcon imRepeat1 ;


    public PlayMusicControl(JButton btnPlay , JButton btnNext , JButton btnPrevious , JButton btnRepeat , JButton btnShuffle , ImageIcon imPause , ImageIcon imPlay , ImageIcon imRepeat , ImageIcon imRepeat1, PlaySlider playSlider, MusicPlayer player) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        this.btnNext=btnNext ;
        this.btnPlay=btnPlay ;
        this.btnPrevious=btnPrevious;
        this.btnRepeat= btnRepeat ;
        this.btnShuffle=btnShuffle ;
        this.imPause=imPause ;
        this.imPlay = imPlay ;
        this.imRepeat= imRepeat ;
        this.imRepeat1 = imRepeat1 ;
        this.playSlider=playSlider ;
        this.player=player ;

        btnShuffle.addActionListener(this);
        btnPrevious.addActionListener(this);
        btnPlay.addActionListener(this);
        btnNext.addActionListener(this);
        btnRepeat.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==btnShuffle){
        /*
        :)
         */
        }
        if( e.getSource()==btnPrevious){
            /*
            :)
             */
        }
        if( e.getSource()==btnPlay)
        {
            if (!isItPlaying) {
                getPlayButton().setIcon(imPause);
                isItPlaying=true ;
                if( firstTime ) {
                    firstTime=false ;
                    try {
                        player.play("src/music/Happier.mp3");
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

        if( e.getSource()==btnNext){
            /*
            :)
             */
        }
        if( e.getSource()==btnRepeat)
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
    }

    public JButton getPlayButton()
    {
        return btnPlay;
    }

    public JButton getRepeatButton() {
        return btnRepeat;
    }

}
