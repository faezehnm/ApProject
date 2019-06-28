package playControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel for displaying equalizer.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class GraphicEqualizerPanel extends JPanel {

    /**
     * Creates an object of class
     */

    public GraphicEqualizerPanel() {

    GraphicEqualizer component = new GraphicEqualizer();

    setBackground(Color.BLACK);

    //setSize(100,100);

    setLayout(new BorderLayout());
    add(component ,BorderLayout.CENTER);


    class TimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            component.move();
        }
    }

    ActionListener listener = new TimeListener();
    Timer timer = new Timer(100, listener);
    timer.start();
}
}
