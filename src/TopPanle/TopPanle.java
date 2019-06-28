package TopPanle;

import display.DisplayListsControl;
import music.Song;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represenrs the top panel and it;s components(name of user and and search part).
 *
 * @author Seyedeh Fatemeh Ahmadzadeh & Faezeh Naeimi
 * @since 2019
 * @version 1.0
 */

public class TopPanle extends JPanel {
    private JLabel jLabel ;
    private Border blueLIne ;
    private JPanel searchPanel;
    private JButton searchBtn;
    private JTextField searchTextField;
    private JComboBox comboBox;
    private String mode [];
    private DisplayListsControl displayListsControl;

    /**
     * Creates an object of this claa
     * @param displayListsControl is an Object of DisplayListsControl class that is controlling the left and center parts.
     */

    public TopPanle(DisplayListsControl displayListsControl){
        this.displayListsControl = displayListsControl;
        setBackground(Color.BLACK);
        jLabel = new JLabel("faezeh");
        setLayout(new BorderLayout());
        add(jLabel , BorderLayout.EAST);
        setSize(new Dimension(1000 , 200));
        blueLIne = BorderFactory.createLineBorder(Color.CYAN);
        jLabel.setForeground(Color.WHITE);
        jLabel.setBackground(Color.BLACK);
        setBorder(blueLIne);
        setSearchPanel();
        add(searchPanel , BorderLayout.CENTER);
    }

    public void setName(String string){
        jLabel.setText(string);

    }

    /**
     * Sets the search panel and it's components and add actionlistenr to it's button.
     */

    private void setSearchPanel(){
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchBtn = new JButton();
        searchTextField = new JTextField();
        mode = new String[3];
        mode[0] = "song";
        mode[1] = "albume";
        mode[2] = "artist";
        comboBox = new JComboBox(mode);
        ImageIcon icon = new ImageIcon("src/Icons/search.png");
        searchBtn.setIcon(icon);
        searchBtn.setBackground(Color.BLACK);
        searchPanel.setSize(new Dimension(400 , 50));
        searchPanel.add(searchBtn , BorderLayout.WEST);
        searchTextField.setEditable(true);
        searchTextField.setSize(400 , 50);
        searchTextField.setBackground(Color.BLACK);
        searchTextField.setForeground(Color.WHITE);
        searchTextField.setBorder(blueLIne);
        searchPanel.setBackground(Color.BLACK);
        searchPanel.add(searchTextField , BorderLayout.CENTER);
        comboBox.setSize(100 , 50);
        comboBox.setBackground(Color.BLACK);
        comboBox.setBorder(blueLIne);
        comboBox.setForeground(Color.WHITE);
        searchPanel.add(comboBox , BorderLayout.EAST);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searched = searchTextField.getText();
                ArrayList<Song> songs = new ArrayList<Song>();
                if(comboBox.getSelectedIndex() == 0) {
                    for (Song song : displayListsControl.getSongs()) {
                        if (song.getName().contains(searched)) {
                            songs.add(song);
                        }
                    }
                    displayListsControl.setDisplaySongs(songs, false);
                }
                if(comboBox.getSelectedIndex() == 1){
                    for(Song song : displayListsControl.getSongs()){
                        if(song.getAlbumeName().contains(searched)){
                            songs.add(song);
                        }
                    }
                    displayListsControl.setDisplaySongs(songs , false);
                }
                if(comboBox.getSelectedIndex() == 2){
                    for(Song song : displayListsControl.getSongs()){
                        if(song.getArtist().contains(searched)){
                            songs.add(song);
                        }
                    }
                    displayListsControl.setDisplaySongs(songs , false);
                }
            }
        });
    }
}
