package display;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;

import static java.awt.Frame.getFrames;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Makes a JFileChooser for choosing an mp3 file and add it to library when "add new song" button is clicked
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public class ChooseMusicFrame {

    private File[] newSong;

    /**
     * This constructor makes an object of this class
     */

    public ChooseMusicFrame() {
        newSong = null;
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("add a new mp3 file to your library");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3 files", "mp3");
        fileChooser.addChoosableFileFilter(filter);
        //fileChooser.setMultiSelectionEnabled(true);
        int returnValue = fileChooser.showDialog(null, "open");
        if (returnValue == JFileChooser.APPROVE_OPTION){
            if(fileChooser.getSelectedFile().isFile()){
                newSong = fileChooser.getSelectedFiles();
            }
        }
    }

    public File[] getNewSong() {
        return newSong;
    }
}
