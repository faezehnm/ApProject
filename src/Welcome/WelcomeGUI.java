package Welcome;

import Network.Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class WelcomeGUI extends JFrame implements ActionListener{

    private Network network ;

    private static final int WIDTH = 500 , HEIGHT = 200;
    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");

    public static LogInGUI logInGUI ;
    public static GoToJPotiy signUpGUI ;

    private JButton signUp ;
    private JButton logIn ;


    public WelcomeGUI() throws IOException
    {
        super();
        setLayout(new GridLayout(2,1));
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setTitle("Welcome to JPotify :)");
        //setIconImage(icon);


        signUp = new JButton("SIGN UP ");
        signUp.addActionListener(this);
        add(signUp);

        logIn = new JButton("LOG IN") ;
        logIn.addActionListener(this);
        add(logIn);

        network = new Network();

    }

    private void actionToLogin()
    {
        logInGUI = new LogInGUI(network);
        setVisible(false);
    }

    private void actionToSignup()
    {
        signUpGUI = new SignUpGUI(network);
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()== signUp)
            actionToSignup();
        if( e.getSource()== logIn)
            actionToLogin();

    }


}
