package playControl;
import VolumeControl.VolumeSlider;
import home.JPotifyGUI;
import music.Song;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serializable;


/**
 * PlayMusicGUI display all components that you see in south of JPotift app
 * @author faezeh naeimi
 * @version 1.0
 * @since 2019
 */
public class PlayMusicGUI extends JPanel {

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
    private GraphicEqualizerPanel graphicEqualizerPanel = new GraphicEqualizerPanel();
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

    private ImageIcon imPlay =new ImageIcon("src/Icons/play.png");
    private ImageIcon imNext =new ImageIcon("src/Icons/next.png");
    private ImageIcon imPrevious =new ImageIcon("src/Icons/back.png");
    private ImageIcon imRepeat =new ImageIcon("src/Icons/iterative.png");
    private ImageIcon imRepeat1 =new ImageIcon("src/Icons/repeat.png");
    private ImageIcon imPause = new ImageIcon("src/Icons/pause.png");
    private ImageIcon imShuffle = new ImageIcon("src/Icons/shuffle (1).png"); ;
    private ImageIcon imShuffle1 =new ImageIcon("src/Icons/infinity.png");

    private Border blueLine = BorderFactory.createLineBorder(Color.CYAN);
    private Border noline = new EmptyBorder(10, 10, 10, 10);

    private JPotifyGUI jPotifyGUI ;

    /**
     *creat a PlayMusicGUI
     * @throws Exception if player is null
     */
    public PlayMusicGUI(JPotifyGUI jPotifyGUI) throws Exception
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
        displayInformationLable = new JLabel() ;

        setSizeOfButton();
        setBorderToAll();
        setBackgroundOfAll();
        setLeftPanelLayout();
        setMiddlePanelLayout();
        setRightPanelLayout();

        add(leftPanel);
        add(middlePanel);
        add(rightPanel);

        this.jPotifyGUI = jPotifyGUI ;

        playMusicControl = new PlayMusicControl(btnPlay , btnNext , btnPrevious , btnRepeat , btnShuffle ,  imPause , imPlay , imRepeat , imRepeat1,playSlider,player ,this,imShuffle,imShuffle1,this.jPotifyGUI,graphicEqualizerPanel , leftPanel , junk3);

    }

    /**
     * adjust background of all componets
     */
    private void setBackgroundOfAll()
    {
        btnPlay.setBackground(Color.BLACK);
        btnNext.setBackground(Color.BLACK);
        btnPrevious.setBackground(Color.BLACK);
        btnRepeat.setBackground(Color.BLACK);
        btnShuffle.setBackground(Color.BLACK);
        leftPanel.setBackground(Color.BLACK);
        rightPanel.setBackground(Color.BLACK);
        middlePanelTop.setBackground(Color.BLACK);
        middletPanelDown.setBackground(Color.BLACK);
        displayInformationLable.setBackground(Color.BLACK);
        displayInformationLable.setForeground(Color.WHITE);
        setBackground(Color.BLACK);
    }

    /**
     * adjust border of all components
     */
    private void setBorderToAll()
    {
        setBorder(blueLine);
    }

    /**
     * adjust size of button
     */
    private void setSizeOfButton()
    {
        btnPlay.setPreferredSize(new Dimension(64, 64));
        btnNext.setPreferredSize(new Dimension(64, 64));
        btnPrevious.setPreferredSize(new Dimension(64, 64));
        btnRepeat.setPreferredSize(new Dimension(64, 64));
        btnShuffle.setPreferredSize(new Dimension(64, 64));
    }

    /**
     * adjust layout of right panel in PlayMusicGUI
     */
    private void setRightPanelLayout(){
        rightPanel.add(volumeSlider);
    }

    /**
     * adjust layout of middle panel in PlayMusicGUI
     */
    private void setMiddlePanelLayout()
    {
        middlePanel.add(middlePanelTop,BorderLayout.NORTH);
        middlePanel.add(middletPanelDown,BorderLayout.SOUTH);

        middlePanelTop.add(btnShuffle);
        middlePanelTop.add(btnPrevious);
        middlePanelTop.add(btnPlay);
        middlePanelTop.add(btnNext);

        middlePanelTop.add(btnRepeat);

        middletPanelDown.add(playSlider);
    }

    /**
     * adjust layout of left panel in PlayMusicGUI
     */
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
        junk.setBackground(Color.BLACK);
        leftPanel.add(junk,gbc2);

        gbc2.gridx = 4;
        gbc2.gridy = 0;
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(10, 0, 0, 0);
        junk2.setBackground(Color.BLACK);
        leftPanel.add(junk2,gbc2);

        gbc2.gridx = 5;
        gbc2.gridy = 0;
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(10, 0, 0, 0);
        junk3.setBackground(Color.BLACK);
        leftPanel.add(junk3,gbc2);
        //leftPanel.add(graphicEqualizerPanel,gbc2);
    }

    /**
     * set song ( use when we want to play another music )
     * @param song a song that we want to play
     * @throws Exception if song is null
     */
    public void setSong( Song song ) throws Exception
    {
        this.song = song ;
        playMusicControl.setSong(song);
        updateSongInformation();
    }
    /**
     * update song information when music change
     */
    private void updateSongInformation()
    {
        if(this.song == null){
            leftPanel.remove(imageLable);
            leftPanel.remove(displayInformationLable);
        }
        else {
            setLeftPanelLayout();
            Image resizedImg = song.scaledImage(100, 100);
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            imageLable.setIcon(resizedIcon);
            displayInformationLable.setText("<html>" + song.getName() + "<br>" + song.getAlbumeName() + "<br>" + song.getArtist() + "<html>");
        }
    }

    /**
     * @return play Button
     */
    public JButton getPlayButton()
    {
        return btnPlay;
    }

    /**
     * @return repeat Button
     */
    public JButton getRepeatButton() {
        return btnRepeat;
    }

    /**
     * @return next Button
     */
    public JButton getBtnNext() {
        return btnNext;
    }

    /**
     * @return Previous Button
     */
    public JButton getBtnPrevious() {
        return btnPrevious;
    }

    /**
     * @return Repeat Button
     */
    public JButton getBtnRepeat() {
        return btnRepeat;
    }

    /**
     * @return Shuffle Button
     */
    public JButton getBtnShuffle() {
        return btnShuffle;
    }

    /**
     * @return image of play button
     */
    public ImageIcon getImPlay() {
        return imPlay;
    }

    /**
     * @return image of Previous button
     */
    public ImageIcon getImPrevious() {
        return imPrevious;
    }

    /**
     * @return image of Repeat button
     */
    public ImageIcon getImRepeat() {
        return imRepeat;
    }

    /**
     * @return  image of Repeat button when it is on reoeat state
     */
    public ImageIcon getImRepeat1() {
        return imRepeat1;
    }

    /**
     * @return image of pause button
     */
    public ImageIcon getImPause() {
        return imPause;
    }

    public Song getSong(){
        return song;
    }
}
