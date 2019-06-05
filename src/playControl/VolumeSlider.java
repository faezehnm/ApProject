package playControl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VolumeSlider extends JPanel implements ChangeListener {
    JSlider jSlider = new JSlider() ;
    private ImageIcon imSpeaker0 =new ImageIcon("src/Icons/speaker0.png");
    private ImageIcon imSpeaker1 =new ImageIcon("src/Icons/speaker1.png");
    private ImageIcon imSpeaker2 =new ImageIcon("src/Icons/speaker2.png");
    private ImageIcon imSpeaker3 =new ImageIcon("src/Icons/speaker3.png");
    JLabel jLabel = new JLabel();

    public VolumeSlider()
    {
        jLabel.setIcon(imSpeaker3);
        add(jLabel);
        add(jSlider);
        jSlider.addChangeListener(this);
        jSlider.setPreferredSize(new Dimension(120,40));
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
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
}
