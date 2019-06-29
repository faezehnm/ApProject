package playControl;
import VolumeControl.VolumeSlider;
import home.JPotifyGUI;
import music.Song;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.xml.ws.Service;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


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
    private JButton lyric;

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
    private Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    private Border noline = new EmptyBorder(10, 10, 10, 10);

    private JPotifyGUI jPotifyGUI ;

    /**
     *creat a PlayMusicGUI
     * @throws Exception if player is null
     */
    public PlayMusicGUI(JPotifyGUI jPotifyGUI) throws Exception
    {
        super(new GridLayout(1,3));

        lyric = new JButton("see the lyric");

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
        setLyricButton();

        add(leftPanel);
        add(middlePanel);
        add(rightPanel);

        this.jPotifyGUI = jPotifyGUI ;

        playMusicControl = new PlayMusicControl(btnPlay , btnNext , btnPrevious , btnRepeat , btnShuffle ,  imPause , imPlay , imRepeat , imRepeat1,playSlider,player ,this,imShuffle,imShuffle1,this.jPotifyGUI,graphicEqualizerPanel , leftPanel , junk3,lyric,junk);

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
        btnPlay.setBorder(blackLine);
        btnNext .setBorder(blackLine);
        btnPrevious .setBorder(blackLine);
        btnRepeat .setBorder(blackLine);
        btnShuffle.setBorder(blackLine);
    }

    /**
     * adjust size of button
     */
    private void setSizeOfButton()
    {
        btnPlay.setPreferredSize(new Dimension(67, 67));
        btnNext.setPreferredSize(new Dimension(67, 67));
        btnPrevious.setPreferredSize(new Dimension(67, 67));
        btnRepeat.setPreferredSize(new Dimension(67, 67));
        btnShuffle.setPreferredSize(new Dimension(67, 67));
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

        //middlePanelTop.setLayout(new GridLayout(1,4,0,0));
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
        //leftPanel.add(lyric,gbc2);



        gbc2.gridx = 5;
        gbc2.gridy = 0;
        gbc2.ipady = 0;
        gbc2.weighty = 1.0;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(10, 0, 0, 0);
        junk3.setBackground(Color.BLACK);
        leftPanel.add(junk3,gbc2);

    }

    /**
     * set song ( use when we want to play another music )
     * @param song a song that we want to play
     * @throws Exception if song is null
     */
    public void setSong( Song song , Boolean mySong ) throws Exception
    {
        this.song = song ;
        playMusicControl.setSong(song , mySong);
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
            Image resizedImg = song.scaledImage(130, 130);
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

    /**
     * set lyric button
     */
    private void setLyricButton()
    {
        lyric.setPreferredSize(new Dimension(100 , 50));
        lyric.setBackground(Color.BLACK);
        lyric.setForeground(Color.WHITE);
        lyric.setBorder(blueLine);
        lyric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame lyricFrame = new JFrame(song.getName() + "lyric");
                lyricFrame.setSize(500, 1500);
                lyricFrame.getContentPane().setBackground(Color.BLACK);
                lyricFrame.setForeground(Color.WHITE);
                String str = new String(song.getArtist());
                str = str.replaceAll("\\s", "");
                str = str.toLowerCase();
                String urlStr;
                String str2 = song.getName();
                str2 = str2.replaceAll("\\s", "");
                str2 = str2.toLowerCase();
                urlStr = "https://www.azlyrics.com/lyrics/" + str + "/" + str2 + ".html";
                System.out.println(urlStr);
                ArrayList<String> lines = new ArrayList<String>();
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                    urlCon.setRequestMethod("GET");
                    //urlCon.setDoOutput(false);
                    //urlCon.setDoInput(true);
                    urlCon.setReadTimeout(10000);
                    urlCon.connect();
                    //InputStream stream=urlCon.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                    String line;
                    while((line = br.readLine()) != null){
                        lines.add(line);
                    }
                    br.close();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JLabel lyricLabel = new JLabel();
                lyricLabel.setBackground(Color.BLACK);
                lyricLabel.setForeground(Color.WHITE);
                lyricLabel.setText("");
                for(String line : lines){
                    lyricLabel.setText(lyricLabel.getText() + "\n" + line);
                }
                lyricFrame.add(lyricLabel , BorderLayout.CENTER);
                lyricFrame.setVisible(true);
            }
        });
    }
}
