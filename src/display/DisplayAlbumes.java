package display;

import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import music.*;

/**
 * This class is used for displaying all albumes on the centre part of GUI. It creates a button for each album and
 * displays the artwork and information of the albume.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class DisplayAlbumes extends DisplaySongsGroup implements Serializable {

   private DisplayListsControl displayListsControl;

    /**
     *Creates and object of this class
     * @param albumes is the list of all albumes
     * @param displayListsControl is an object of DisplayListsControl class that is controling the left panel
     * @param playMusicGUIl is an object of PlayMusic GUI which is added to the south of main frame
     * @throws Exception
     */

    public DisplayAlbumes(ArrayList<Albume> albumes , DisplayListsControl displayListsControl , PlayMusicGUI playMusicGUIl) throws Exception{
        super(albumes , playMusicGUIl);
        this.displayListsControl = displayListsControl;
    }

    /**
     * Adds actionlistener to each albume button
     * @param btn is the albume button
     * @param music is a music from albume kind
     */

    @Override
    protected void addActionListeners(JButton btn , Music music){
        Albume albume = (Albume) music;
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println(":|");
                try {
                    displayListsControl.setDisplaySongs(albume.getSongs());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
