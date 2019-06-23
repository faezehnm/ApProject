package playControl;
import VolumeControl.VolumeSlider;
import music.Song;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *The PlayMusicGUI provides a variety of features such as music playlists, playback capabilities, music podcasts and streaming music, as well as playback of the next and previous music, as well as music repetition and music overlays.
 */

public class PlayMusicGUI extends JPanel  {

    private GridBagConstraints gbc;
    private GridBagConstraints gbc2;
    private JPanel junk = new JPanel();
    private JPanel junk2 = new JPanel();
    private JPanel junk3 = new JPanel();

    private MusicPlayer player = new MusicPlayer();
    private PlaySlider playSlider = new PlaySlider(player);
    private VolumeSlider volumeSlider = new VolumeSlider();
    private Song song ;
    private PlayMusicControl playMusicControl ;

    private JPanel leftPanel = new JPanel() ;
    private JPanel rightPanel = new JPanel() ;
    private JPanel middlePanel = new JPanel(new GridLayout(2,1)) ;
    private JPanel middlePanelTop = new JPanel() ;
    private JPanel middletPanelDown = new JPanel() ;
    private JLabel displayInformationLable ;
    private JLabel imageLable ;

    private JButton btnPlay ;
    private JButton btnNext ;
    private JButton btnPrevious ;
    private JButton btnRepeat ;
    private JButton btnShuffle ;
    private ImageIcon imPlay =new ImageIcon("src/Icons/play-button.png");
    private ImageIcon imNext =new ImageIcon("src/Icons/next.png");
    private ImageIcon imPrevious =new ImageIcon("src/Icons/back.png");
    private ImageIcon imRepeat =new ImageIcon("src/Icons/repeat.png");
    private ImageIcon imRepeat1 =new ImageIcon("src/Icons/repeat1.png");
    private ImageIcon imPause = new ImageIcon("src/Icons/pause.png");
    private ImageIcon imShuffle = new ImageIcon("src/Icons/shuffle-arrows.png"); ;
    private ImageIcon imShuffle1 =new ImageIcon("src/Icons/shuffle1.png");
    private Border blackline = BorderFactory.createLineBorder(Color.GREEN);
    private Border noline = new EmptyBorder(10, 10, 10, 10);


    public PlayMusicGUI() throws Exception
    {
        super(new GridLayout(1,3));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc2 = new GridBagConstraints();

        imageLable = new JLabel();

        btnPlay = new JButton(imPlay);
        btnNext = new JButton(imNext);
        btnPrevious = new JButton(imPrevious);
        btnRepeat = new JButton(imRepeat1);
        btnShuffle = new JButton(imShuffle) ;

        btnPlay.setPreferredSize(new Dimension(34, 34));
        btnNext.setPreferredSize(new Dimension(34, 34));
        btnPrevious.setPreferredSize(new Dimension(34, 34));
        btnRepeat.setPreferredSize(new Dimension(34, 34));
        btnShuffle.setPreferredSize(new Dimension(34, 34));

        btnPlay.setBorder(noline);
        btnNext.setBorder(noline);
        btnPrevious.setBorder(noline);
        btnRepeat.setBorder(noline);
        btnShuffle.setBorder(noline);
        setBorder(blackline);


        btnPlay.setBackground(Color.WHITE);
        btnNext.setBackground(Color.white);
        btnPrevious.setBackground(Color.white);
        btnRepeat.setBackground(Color.white);
        btnShuffle.setBackground(Color.white);
        leftPanel.setBackground(Color.white);
        rightPanel.setBackground(Color.white);
        middlePanelTop.setBackground(Color.white);
        middletPanelDown.setBackground(Color.white);
        setBackground(Color.white);

        displayInformationLable = new JLabel() ;
        displayInformationLable.setBackground(Color.white);

        setLeftPanelLayout();

        middlePanel.add(middlePanelTop,BorderLayout.NORTH);
        middlePanel.add(middletPanelDown,BorderLayout.SOUTH);

        middlePanelTop.add(btnShuffle);
        middlePanelTop.add(btnPrevious);
        middlePanelTop.add(btnPlay);
        middlePanelTop.add(btnNext);

        middlePanelTop.add(btnRepeat);

        middletPanelDown.add(playSlider);

        rightPanel.add(volumeSlider);

        add(leftPanel);
        add(middlePanel);
        add(rightPanel);

        playMusicControl = new PlayMusicControl(btnPlay , btnNext , btnPrevious , btnRepeat , btnShuffle ,  imPause , imPlay , imRepeat , imRepeat1,playSlider,player ,this,imShuffle,imShuffle1);

    }

    private void setLeftPanelLayout()
    {
        leftPanel.setLayout(new GridLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(imageLable,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        leftPanel.add(displayInformationLable,gbc);
        gbc2.gridx = 3;
        gbc2.gridy = 0;
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(10, 0, 0, 0);
        junk.setBackground(Color.white);
        leftPanel.add(junk,gbc2);

        gbc2.gridx = 4;
        gbc2.gridy = 0;
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(10, 0, 0, 0);
        junk2.setBackground(Color.white);
        leftPanel.add(junk2,gbc2);

        gbc2.gridx = 5;
        gbc2.gridy = 0;
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(10, 0, 0, 0);
        junk3.setBackground(Color.white);
        leftPanel.add(junk3,gbc2);
    }

    public void setSong( Song song ) throws Exception
    {
        this.song = song ;
        playMusicControl.setSong(song);
        updateSongInformation();
    }

    private void updateSongInformation()
    {
        Image resizedImg = song.scaledImage(100, 100);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        imageLable.setIcon(resizedIcon);
        displayInformationLable.setText("<html>"+song.getMusicName()+"<br>"+song.getAlbumeName()+"<br>"+song.getArtist()+"<html>");
    }

    public PlayMusicControl getPlayMusicControl(){
        return playMusicControl ;
    }

    public JButton getPlayButton()
    {
        return btnPlay;
    }

    public JButton getRepeatButton() {
        return btnRepeat;
    }

    public JButton getBtnPlay() {
        return btnPlay;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public JButton getBtnPrevious() {
        return btnPrevious;
    }

    public JButton getBtnRepeat() {
        return btnRepeat;
    }

    public JButton getBtnShuffle() {
        return btnShuffle;
    }

    public ImageIcon getImPlay() {
        return imPlay;
    }

    public ImageIcon getImPrevious() {
        return imPrevious;
    }

    public ImageIcon getImRepeat() {
        return imRepeat;
    }

    public ImageIcon getImRepeat1() {
        return imRepeat1;
    }

    public ImageIcon getImPause() {
        return imPause;
    }
}
