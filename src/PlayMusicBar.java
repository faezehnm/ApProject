import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;


public class PlayMusicBar extends JPanel implements ActionListener {
    File file = new File("src/music/Happier.mp3");
    MusicPlayer player = new MusicPlayer();
    private int pausedOnFrame = 0;
    private boolean isItPlaying=false;
    private boolean firstTime=true;
    Song song = new Song("src/music/Happier.mp3");
    DisplayInformation displayInformation = new DisplayInformation(song.getMusicName(),song.getAlbumnane(),song.getArtist());
    PlaySlider playSlider = new PlaySlider();

    JButton btnPlay ;
    JButton btnNext ;
    JButton btnPrevious ;
    JButton btnRepeat ;
    JButton btnShuffle ;
    ImageIcon imPlay =new ImageIcon("src/Icons/play-button.png");
    ImageIcon imNext =new ImageIcon("src/Icons/next.png");
    ImageIcon imPrevious =new ImageIcon("src/Icons/back.png");
    ImageIcon imRepeat =new ImageIcon("src/Icons/repeat.png");
    ImageIcon imShuffle =new ImageIcon("src/Icons/shuffle.png");
    ImageIcon imPause = new ImageIcon("src/Icons/pause.png");


    public PlayMusicBar() throws Exception {
        super(new GridLayout(1,8,10,0));

        btnPlay = new JButton(imPlay);
        btnNext = new JButton(imNext);
        btnPrevious = new JButton(imPrevious);
        btnRepeat = new JButton(imRepeat);
        btnShuffle = new JButton(imShuffle) ;

        btnPlay.setPreferredSize(new Dimension(34, 34));
        btnNext.setPreferredSize(new Dimension(34, 34));
        btnPrevious.setPreferredSize(new Dimension(34, 34));
        btnRepeat.setPreferredSize(new Dimension(34, 34));
        btnShuffle.setPreferredSize(new Dimension(34, 34));

        btnPlay.setBackground(Color.WHITE);
        btnNext.setBackground(Color.white);
        btnPrevious.setBackground(Color.white);
        btnRepeat.setBackground(Color.white);
        btnShuffle.setBackground(Color.white);

        add(displayInformation);
        add(btnShuffle);
        add(btnPrevious);
        add(btnPlay);
        add(btnNext);
        add(btnRepeat);
        add(playSlider,BorderLayout.PAGE_END);


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
        if( e.getSource()==btnPlay){
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
                isItPlaying= false ;
            }

        }

        if( e.getSource()==btnNext){
            /*
            :)
             */
        }
        if( e.getSource()==btnRepeat){
            player.setRepeat(true);
        }
    }

    public JButton getPlayButton() {
        return btnPlay;
    }

}
