package VolumeControl;

import com.sun.media.jfxmedia.Media;
import music.Song;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class VolumeSlider extends JPanel implements ChangeListener {
    private JSlider jSlider;
    private ImageIcon imSpeaker0 =new ImageIcon("src/Icons/speaker0.png");
    private ImageIcon imSpeaker1 =new ImageIcon("src/Icons/speaker1.png");
    private ImageIcon imSpeaker2 =new ImageIcon("src/Icons/speaker2.png");
    private ImageIcon imSpeaker3 =new ImageIcon("src/Icons/speaker3.png");
    private JLabel jLabel = new JLabel();

    public VolumeSlider()
    {
        jSlider = new JSlider(JSlider.HORIZONTAL , 0 , 100 , 50);
        jSlider.setMajorTickSpacing(50);
        jSlider.setPaintTicks(true);
        jSlider.setForeground(Color.CYAN);
        jLabel.setIcon(imSpeaker3);
        add(jLabel);
        add(jSlider);
        jSlider.addChangeListener(this);
        jSlider.setPreferredSize(new Dimension(120,40));
        setBackground(Color.white);
        jSlider.setBackground(Color.white);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        Line.Info source = Port.Info.SPEAKER;
        if(AudioSystem.isLineSupported(source)){
            try {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                volumeControl.setValue((float)jSlider.getValue() / 100);
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }

        }

        updateImage();
    }

    public void updateImage()
    {
        boolean zero = false ;
        boolean first =false ;
        boolean second =false ;
        boolean third =false ;


        if( jSlider.getValue()==0)
            zero= true ;
        else if(0<jSlider.getValue()&& jSlider.getValue()<=jSlider.getMaximum()/3)
            first= true;
        else if(jSlider.getMaximum()/3<jSlider.getValue()&& jSlider.getValue()<=2*jSlider.getMaximum()/3)
            second= true;
        else
            third= true;


        if(zero)
            jLabel.setIcon(imSpeaker0);
        if(first)
            jLabel.setIcon(imSpeaker1);
        if(second)
            jLabel.setIcon(imSpeaker2);
        if(third)
            jLabel.setIcon(imSpeaker3);

    }
    private static float limit(FloatControl control,float level)
    { return Math.min(control.getMaximum(), Math.max(control.getMinimum(), level)); }

}
