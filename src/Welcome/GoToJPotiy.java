package Welcome;

import home.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GoToJPotiy extends JFrame implements ActionListener {

    private static final int WIDTH = 400 , HEIGHT = 200;
    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");

    private JLabel jLabelPass;
    private JLabel jLabelName;
    private JTextField jTextFieldPass;
    private JTextField jTextFieldName;
    private JButton jButton ;
    private String pass ;
    private String name ;

    public GoToJPotiy(){
        super();
        setLayout(new GridLayout(4,1));
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setIconImage(icon);

        jLabelName = new JLabel("Enter your name :");
        add(jLabelName);

        jTextFieldName = new JTextField();
        jTextFieldName.addKeyListener(listener);
        add(jTextFieldName);

        jLabelPass = new JLabel("Enter your password :");
        add(jLabelPass);

        jTextFieldPass = new JTextField();
        jTextFieldPass.addKeyListener(listener);
        add(jTextFieldPass);

        jButton = new JButton("GO :)");
        jButton.addActionListener(this);
        add(jButton);

        pass = new String() ;
        name = new String();

    }

    public void actionToDo() throws Exception {
        name = jTextFieldName.getText() ;
        pass = jTextFieldPass.getText() ;

        User user = new User(name,pass);

        actionToDoForEeach(user);

    }

    public abstract void actionToDoForEeach(User user) throws Exception;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            actionToDo();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    actionToDo();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    };

    public JButton getjButton(){
        return jButton;
    }
}
