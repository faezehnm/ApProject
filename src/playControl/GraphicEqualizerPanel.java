package playControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicEqualizerPanel extends JPanel {

    public GraphicEqualizerPanel() {

    GraphicEqualizer component = new GraphicEqualizer();

    add(component);

    setBackground(Color.pink);

    setSize(40,40);

    setLayout(new BorderLayout());
    add(component ,BorderLayout.CENTER);


    class TimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            component.move(2, 3);
        }
    }

    ActionListener listener = new TimeListener();
    Timer timer = new Timer(200, listener);
        timer.start();
}
}
