package TopPanle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TopPanle extends JPanel {
    private JLabel jLabel ;
    Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);

    public TopPanle(){
        setBackground(Color.white);
        jLabel = new JLabel("faezeh");
        add(jLabel);
        setBorder(greenLIne);
    }

    public void setName(String string){
        jLabel.setText(string);

    }
}
