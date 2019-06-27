package TopPanle;

import display.DisplayListsControl;
import music.Song;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TopPanle extends JPanel {
    private JLabel jLabel ;
    private Border blueLIne ;
    private JPanel searchPanel;
    private JButton searchBtn;
    private JTextField searchTextField;
    private DisplayListsControl displayListsControl;

    public TopPanle(DisplayListsControl displayListsControl){
        this.displayListsControl = displayListsControl;
        setSearchPanel();
        setBackground(Color.BLACK);
        jLabel = new JLabel("faezeh");
        setLayout(new BorderLayout());
        add(jLabel , BorderLayout.EAST);
        setSize(new Dimension(1000 , 200));
        blueLIne = BorderFactory.createLineBorder(Color.CYAN);
        jLabel.setForeground(Color.WHITE);
        jLabel.setBackground(Color.BLACK);
        setBorder(blueLIne);
        add(searchPanel , BorderLayout.CENTER);
    }

    public void setName(String string){
        jLabel.setText(string);

    }

    private void setSearchPanel(){
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchBtn = new JButton();
        searchTextField = new JTextField();
        ImageIcon icon = new ImageIcon("src/Icons/search.png");
        searchBtn.setIcon(icon);
        searchBtn.setBackground(Color.BLACK);
        searchPanel.setSize(new Dimension(400 , 50));
        searchPanel.add(searchBtn , BorderLayout.WEST);
        searchTextField.setEditable(true);
        searchTextField.setSize(100 , 50);
        searchTextField.setBackground(Color.BLACK);
        searchTextField.setForeground(Color.WHITE);
        searchTextField.setBorder(blueLIne);
        searchPanel.setBackground(Color.BLACK);
        searchPanel.add(searchTextField , BorderLayout.CENTER);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searched = searchTextField.getText();
                ArrayList<Song> songs = new ArrayList<Song>();
                for(Song song : displayListsControl.getSongs()){
                    if(song.getName().contains(searched)){
                        songs.add(song);
                    }
                }
                displayListsControl.setDisplaySongs(songs , false);
            }
        });
    }
}
