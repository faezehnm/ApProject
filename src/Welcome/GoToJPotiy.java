package Welcome;

import Network.User;
import Network.UserState;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import static Network.UserState.*;

public abstract class GoToJPotiy extends JFrame implements ActionListener , Serializable {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");
    private JLabel jLabelPass;
    private JLabel jLabelName;
    private JTextField jTextFieldPass;
    private JTextField jTextFieldName;
    private JButton jButton;
    private String pass;
    private String name;


    public GoToJPotiy() {
        this.setLayout(new GridLayout(4, 1));
        this.setVisible(true);
        this.setSize(400, 200);
        this.setIconImage(this.icon);

        this.jLabelName = new JLabel("Enter your name :");
        this.add(this.jLabelName);

        this.jTextFieldName = new JTextField();
        this.jTextFieldName.addKeyListener(this.listener);

        this.add(this.jTextFieldName);
        this.jLabelPass = new JLabel("Enter your password :");

        this.add(this.jLabelPass);
        this.jTextFieldPass = new JTextField();

        this.jTextFieldPass.addKeyListener(this.listener);
        this.add(this.jTextFieldPass);

        this.jButton = new JButton("GO :)");
        this.jButton.addActionListener(this);
        this.add(this.jButton);

        this.pass = new String();
        this.name = new String();
    }

    public void actionToDo() throws Exception {
        name = this.jTextFieldName.getText();
        pass = this.jTextFieldPass.getText();
        User user = new User(name, pass, ME );
        actionToDoForEeach(user);
    }

    public abstract void actionToDoForEeach(User user) throws Exception;

    public void actionPerformed(ActionEvent e) {
        try {
            actionToDo();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    KeyListener listener = new KeyListener() {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                try {
                    GoToJPotiy.this.actionToDo();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

        }

        public void keyReleased(KeyEvent e) {
        }
    };

}
