package music;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * It represents a music and can be a song or albume and has a name and artwork.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class Music {

    protected String name;
    protected BufferedImage artwork;

    public String getName() {
        return name;
    }

    public BufferedImage getArtwork() {
        return artwork;
    }

    /**
     * Make an Image of artwork in the given size
     * @param width is width of requested image
     * @param height is height of requested image
     * @return the requsted image in the given size
     */

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
