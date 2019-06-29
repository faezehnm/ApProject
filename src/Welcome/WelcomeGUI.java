package Welcome;

import Network.Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

/**
 * first window which displays to go in JPotify
 * @author faezeh naeimi
 * @version 1.0
 * @since 2019
 */
public class WelcomeGUI extends JFrame implements ActionListener , Serializable {


    private static final int WIDTH = 500 , HEIGHT = 200;
    private Image icon = Toolkit.getDefaultToolkit().getImage("src/Icons/musical-notes-symbols.png");

    public static LogInGUI logInGUI ;
    public static GoToJPotiy signUpGUI ;

    private JButton signUp ;
    private JButton logIn ;

    /**
     * creat WelcomeGUI
     * @throws IOException
     */
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


    }

    /**
     * action to log in
     */
    private void actionToLogin()
    {
        logInGUI = new LogInGUI();
        setVisible(false);
    }

    /**
    action to sign up
     */
    private void actionToSignup()
    {
        signUpGUI = new SignUpGUI();
        setVisible(false);
    }

    /**
     * action to buttons
     * @param e when click on buttons
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()== signUp)
            actionToSignup();
        if( e.getSource()== logIn)
            actionToLogin();

    }


}
