package playControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicEqualizerPanel extends JPanel {

    public GraphicEqualizerPanel() {

    GraphicEqualizer component = new GraphicEqualizer();

    setBackground(Color.PINK);

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
