package playControl;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicEqualizer extends JComponent {

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;

    public GraphicEqualizer(){
        rectangle1 = new Rectangle(new Point(0 , 200));
        rectangle2 = new Rectangle(new Point(10 , 200));
        rectangle3 = new Rectangle(new Point(20 , 200));
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(rectangle1);
        g2d.draw(rectangle2);
        g2d.draw(rectangle3);
    }

    public void move(){
        Random random = new Random();
        rectangle1.setBounds(0 , 200 , 10 , 0);
        rectangle2.setBounds(10 , 200 , 10 , 0);
        rectangle3.setBounds(20 , 200 , 10 , 0);
        rectangle1.add(new Point(10 ,200 - random.nextInt(40)));
        rectangle2.add(new Point(20 , 200 - random.nextInt(40)));
        rectangle3.add(new Point(30 , 200 - random.nextInt(40)));
        repaint();
    }
}
