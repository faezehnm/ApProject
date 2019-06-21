package music;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Music {

    protected String name;
    protected BufferedImage artwork;

    public String getName() {
        return name;
    }

    public BufferedImage getArtwork() {
        return artwork;
    }

    public Image scaledImage(int width , int height){
        Image img;
        if(artwork != null){
            img = (Image) artwork;
        }
        else{
            ImageIcon imgIcon = new ImageIcon("src/Icons/music icon.png");
            img = imgIcon.getImage();
        }
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
        return resizedImage;
    }

}
