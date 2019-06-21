package playControl;
import VolumeControl.VolumeSlider;
import other.Song;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class PlayMusicGUI extends JPanel  {

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
    private ImageIcon imShuffle =new ImageIcon("src/Icons/shuffle.png");
    private ImageIcon imPause = new ImageIcon("src/Icons/pause.png");
    private Border blackline = BorderFactory.createLineBorder(Color.GREEN);
    private Border noline = new EmptyBorder(10, 10, 10, 10);


    public PlayMusicGUI() throws Exception
    {
        super(new GridLayout(1,3));

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
        displayInformationLable.setBackground(Color.white);

        leftPanel.add(displayInformationLable);
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

        playMusicControl = new PlayMusicControl(btnPlay , btnNext , btnPrevious , btnRepeat , btnShuffle ,  imPause , imPlay , imRepeat , imRepeat1,playSlider,player );

    }

    public void setSong( Song song ) throws Exception {
        this.song = song ;
        playMusicControl.setSong(song);
        updateSongInformation();
    }

    private void updateSongInformation(){
        displayInformationLable.setText("<html>"+song.getMusicName()+"<br>"+song.getAlbumnane()+"<br>"+song.getArtist()+"<html>");
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
