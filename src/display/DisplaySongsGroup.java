package display;

import home.JPotifyGUI;
import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import music.*;

/**
 * This class is for displaying musics(songs and albumes) in the center partr of the main frame.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public abstract class DisplaySongsGroup extends JScrollPane {

    protected JPanel panel;
    protected static ArrayList<? extends Music> musics;
    protected ArrayList<JPanel> musicsPanels;
    protected PlayMusicGUI playMusicGUI;
    protected JPotifyGUI mainGUI;

    /**
     * Creates an object of DisplaySongsGroup class and adjust all it's components including panels , buttons and labels.
     * @param musics is a list musics to be displayed.
     * @param playMusicGUI is an object of PlayMusicGUI class that is added to mainGUI.
     */

    public DisplaySongsGroup(ArrayList<? extends Music> musics , PlayMusicGUI playMusicGUI , JPotifyGUI mainGUI){
        panel = new JPanel();
        musicsPanels = new ArrayList<JPanel>();
        this.musics = musics;
        this.playMusicGUI = playMusicGUI;
        this.mainGUI = mainGUI;
        panel.setBackground(Color.WHITE);
        setViewportView(panel);
        setBackground(Color.WHITE);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setLayout(layout);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        int counter = 0;
        for(Music music : musics){
            JPanel pnl = new JPanel();
            pnl.setBackground(Color.WHITE);
            pnl.setBorder(greenLIne);
            GridBagLayout innerLayout = new GridBagLayout();
            GridBagConstraints innerGbc = new GridBagConstraints();
            pnl.setLayout(innerLayout);
            pnl.setSize(new Dimension(370 , 420));
            innerGbc.gridx = 0;
            innerGbc.gridy = 0;
            JButton btn = createButton(music);
            pnl.add(btn , innerGbc);
            try {
                addActionListeners(btn , music , pnl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            innerGbc.gridx = 0;
            innerGbc.gridy = 1;
            pnl.add(createLabel(music) , innerGbc);
            gbc.gridx = counter % 4;
            gbc.gridy = counter / 4;
            panel.add(pnl , gbc);
            musicsPanels.add(pnl);
            counter++;
        }
    }

    /**
     * Creates a button for the given music
     * @param music is the given music that we want to make a button for.
     * @return the made button
     */

    private JButton createButton(Music music){
        JButton btn = new JButton();
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        btn.setBorder(greenLIne);
        if(music.getArtwork() != null) {
            Image resizedImg = music.scaledImage(370, 370);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
        }
        else{
            Image resizedImg = music.scaledImage(370 , 370);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            btn.setIcon(resizedIcon);
        }
        btn.setSize(new Dimension(370 , 370));
        btn.setBackground(Color.WHITE);
        return btn;
    }

    /**
     * Creates a label for the given music and displays it's information on the label.
     * @param music is the given music that we want to make a label for.
     * @return
     */

    private JLabel createLabel(Music music) {
        JLabel lbl = new JLabel();
        lbl.setBackground(Color.white);
        lbl.setPreferredSize(new Dimension(370, 50));
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        lbl.setBorder(greenLIne);
        lbl.setText(music.getName());
        if(music instanceof Song){
            lbl.setText(lbl.getText() + "   " + ((Song) music).getAlbumeName() + "   " + ((Song) music).getArtist());
        }
        return lbl;
    }

    /**
     * Adds actionlistener to each music button
     * @param btn is the music button
     * @param music is the music that we want add actionlistener to it's button
     */

    protected abstract void addActionListeners(JButton btn , Music music , JPanel pnl);

    public ArrayList<? extends Music> getMusics() {
        return musics;
    }

    /**
     * Finds and returns the next song in the playlist , albume or library
     * @param song is the song which is playing now
     * @return the next song.
     */

    public static Song returnNext(Song song){
        int index = musics.indexOf(song);
        if(index + 1 != musics.size()) {
            return (Song) musics.get(index + 1);
        }
        else{
            return (Song) musics.get(0);
        }
    }

    /**
     * Finds and returns the pervious song in the playlist , albume or library
     * @param song is the song which is playing now
     * @return the previous song.
     */

    public static Song returnPrevious(Song song){
        int index = musics.indexOf(song);
        if(index == 0){
            return (Song) musics.get(musics.size() - 1);
        }
        else{
            return (Song) musics.get(index - 1);
        }
    }

    /**
     * Returns a random song in the library , albume or playlist.
     * @param song is the song which is playing now
     * @return a random song
     */

    public static Song returnShuffle(Song song){
        Random rand = new Random();
        int random;
        do{
             random = rand.nextInt(musics.size());
        }while (random == musics.indexOf(song));
        return (Song) musics.get(random);
    }
}
