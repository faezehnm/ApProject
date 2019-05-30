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

public class PlayMusicBar extends JPanel implements ActionListener ,Runnable{
    File file = new File("src/music/Happier.mp3");
    FileInputStream fs = new FileInputStream(file);
    BufferedInputStream bi = new BufferedInputStream(fs) ;
    AdvancedPlayer player = new AdvancedPlayer(bi);
    private int pausedOnFrame = 0;

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

    private boolean isItPlaying;

    public PlayMusicBar() throws FileNotFoundException, JavaLayerException {
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

        add(btnShuffle);
        add(btnPrevious);
        add(btnPlay);
        add(btnNext);
        add(btnRepeat);

        btnShuffle.addActionListener(this);
        btnPrevious.addActionListener(this);
        btnPlay.addActionListener(this);
        btnNext.addActionListener(this);
        btnRepeat.addActionListener(this);

        isItPlaying = false;


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
        Thread thread= new Thread(this);
        if( e.getSource()==btnPlay){
            if (!isItPlaying) {
                getPlayButton().setIcon(imPause);
                thread.start();

            } else {
                getPlayButton().setIcon(imPlay);
                thread.start();
            }
        }
        if( e.getSource()==btnNext){
            /*
            :)
             */
        }
        if( e.getSource()==btnRepeat){
            /*

             */
        }
    }

    public JButton getPlayButton() {
    return btnPlay;
    }

    @Override
    public void run() {

        System.out.println(isItPlaying);
        System.out.println(pausedOnFrame);
        if( !isItPlaying){
            try {
                isItPlaying = true;
                player.play(pausedOnFrame, Integer.MAX_VALUE);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
        else if( isItPlaying){
            player.setPlayBackListener(new PlaybackListener() {
               @Override
               public void playbackFinished(PlaybackEvent event) {
                   pausedOnFrame = event.getFrame();
               }
           });
            player.stop();
            isItPlaying = false;
       }

    }
}
