package Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Warning extends JFrame implements ActionListener {
    private static final int WIDTH = 500 , HEIGHT = 200;
    private JLabel jLabel ;
    private JButton jButton;

    public Warning(String string){
        super();
        setLayout(new GridLayout(2,1));
        jLabel= new JLabel();
        jLabel.setText(string);
        jButton = new JButton("OK");
        jButton.addActionListener(this);
        add(jLabel);
        add(jButton);
        setSize(WIDTH, HEIGHT);
        setTitle("warning");
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==jButton)
            setVisible(false);
    }
}
