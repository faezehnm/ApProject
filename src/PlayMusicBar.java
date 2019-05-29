import javax.swing.*;
import java.awt.*;

public class PlayMusicBar extends JPanel {
    JButton btnPlay ;
    JButton btnNext ;
    JButton btnPrevious ;
    JButton btnRepeat ;
    JButton btnShuffle ;


    ImageIcon imPlay =new ImageIcon("C:\\Users\\naimi\\Desktop\\Icons\\play-button.png");
    ImageIcon imNext =new ImageIcon("C:\\Users\\naimi\\Desktop\\Icons\\next.png");
    ImageIcon imPrevious =new ImageIcon("C:\\Users\\naimi\\Desktop\\Icons\\back.png");
    ImageIcon imRepeat =new ImageIcon("C:\\Users\\naimi\\Desktop\\Icons\\repeat.png");
    ImageIcon imShuffle =new ImageIcon("C:\\Users\\naimi\\Desktop\\Icons\\shuffle.png");
    ImageIcon imPause = new ImageIcon("C:\\Users\\naimi\\Desktop\\Icons\\pause.png");

    public PlayMusicBar(){
        super();
        setBackground(Color.WHITE);
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


    }
}
