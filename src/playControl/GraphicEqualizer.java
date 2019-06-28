package playControl;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * This class is for creating animating egulizer.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class GraphicEqualizer extends JComponent {

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle4;
    private Rectangle rectangle5;
    private Rectangle rectangle6;
    private Rectangle rectangle7;

    /**
     * Creates an object of class
     */

    public GraphicEqualizer(){
        rectangle1 = new Rectangle(new Point(0 , 100));
        rectangle2 = new Rectangle(new Point(10 , 100));
        rectangle3 = new Rectangle(new Point(20 , 100));
        rectangle4 = new Rectangle(new Point(30 , 100));
        rectangle5 = new Rectangle(new Point(40 , 100));
        rectangle6 = new Rectangle(new Point(50 , 100));
        rectangle7 = new Rectangle(new Point(60 , 100));
    }

    /**
     * draws shaps at first
     * @param g
     */

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.draw(rectangle1);
        g2d.draw(rectangle2);
        g2d.draw(rectangle3);
        g2d.draw(rectangle4);
        g2d.draw(rectangle5);
        g2d.draw(rectangle6);
        g2d.draw(rectangle7);
    }

    /**
     * Chnges the position of shapes by random.
     */

    public void move(){

        Random random = new Random();
        rectangle1.setBounds(0 , 100 , 10 , 0);
        rectangle2.setBounds(10 , 100 , 10 , 0);
        rectangle3.setBounds(20 , 100 , 10 , 0);
        rectangle4.setBounds(30 , 100 , 10 , 0);
        rectangle5.setBounds(40 , 100 , 10 , 0);
        rectangle6.setBounds(50 , 100 , 10 , 0);
        rectangle7.setBounds(60 , 100 , 10 , 0);
        rectangle1.add(new Point(10 ,100 - random.nextInt(50)));
        rectangle2.add(new Point(20 , 100 - random.nextInt(50)));
        rectangle3.add(new Point(30 , 100 - random.nextInt(50)));
        rectangle4.add(new Point(40 , 100 - random.nextInt(50)));
        rectangle5.add(new Point(50 , 100 - random.nextInt(50)));
        rectangle6.add(new Point(60 , 100 - random.nextInt(50)));
        rectangle7.add(new Point(70 , 100 - random.nextInt(50)));
        repaint();
    }
}
