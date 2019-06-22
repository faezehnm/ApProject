package display;

import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import music.*;

public class DisplayAlbumes extends DisplaySongsGroup {

   private DisplayListsControl displayListsControl;

    public DisplayAlbumes(ArrayList<Albume> albumes , DisplayListsControl displayListsControl , PlayMusicGUI playMusicGUIl) throws Exception{
        super(albumes , playMusicGUIl);
        this.displayListsControl = displayListsControl;
    }

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
