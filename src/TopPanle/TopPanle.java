package TopPanle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TopPanle extends JPanel {
    private JLabel jLabel ;
    Border blueLIne = BorderFactory.createLineBorder(Color.CYAN);

    public TopPanle(){
        setBackground(Color.BLACK);
        jLabel = new JLabel("faezeh");
        add(jLabel);
        jLabel.setForeground(Color.WHITE);
        jLabel.setBackground(Color.BLACK);
        setBorder(blueLIne);
    }

    public void setName(String string){
        jLabel.setText(string);

    }
}
