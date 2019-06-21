package display;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ChooseMusicFrame {

    private File newSong = null;

    public ChooseMusicFrame() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("add a new mp3 file to your library");
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            if(fileChooser.getSelectedFile().isFile()){
                newSong = fileChooser.getSelectedFile();
            }
        }
    }

    public File getNewSong() {
        return newSong;
    }
}
