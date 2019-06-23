package display;
import home.JPotifyGUI;
import playControl.PlayMusicGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import music.*;

import static com.sun.javafx.fxml.expression.Expression.add;


public class DisplayListsGUI extends JScrollPane {

    private DisplayListsControl displayListsControl;
    private JPanel listsPnl;
    private JButton addNewMusic;
    private JButton addNewPlaylist;
    private JButton songsbutton;
    private JButton albumebutton;
    private JLabel playlistlbl;
    private ArrayList<JButton> playlistsbtn;
    private JPotifyGUI mainGUI;
    private PlayMusicGUI playMusicGUI;
    private JPanel createPlaylistPanel;
    private JButton finishedCooshingBtn;
    private GridBagConstraints gbc;
    private GridBagConstraints gbc2;
    private int numberOfPlaylists;

    public DisplayListsGUI(JPotifyGUI mainGUI , PlayMusicGUI playMusicGUI) throws Exception{
        displayListsControl = new DisplayListsControl(mainGUI , playMusicGUI);
        playlistsbtn = new ArrayList<JButton>();
        this.mainGUI = mainGUI;
        this.playMusicGUI = playMusicGUI;
        listsPnl = new JPanel();
        creatListsPnl();
    }

    private void creatListsPnl() {
        setViewportView(listsPnl);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc2 = new GridBagConstraints();
        listsPnl.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addNewMusic = new JButton("Add music");
        songsbutton = new JButton("Songs");
        albumebutton = new JButton("Albums");
        playlistlbl = new JLabel("playlists :");
        addNewPlaylist = new JButton("new playlist");
        createPlaylistPanel = new JPanel();
        finishedCooshingBtn = new JButton("choosing finished");
        ImageIcon image = new ImageIcon("src/Icons/add2.png");
        addNewMusic.setIcon(image);
        addNewPlaylist.setIcon(image);

        setSize();
        setColor();
        setBorder();
        addActionListeners();

        gbc.gridx = 0;
        gbc.gridy = 0;
        listsPnl.add(addNewMusic, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        listsPnl.add(songsbutton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        listsPnl.add(albumebutton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        listsPnl.add(playlistlbl, gbc);
        numberOfPlaylists = 0;
        if (displayListsControl.getPlaylists().size() != 0) {
            for (PlayList playList : displayListsControl.getPlaylists()) {
                JButton playlistbtn = new JButton(playList.getPlayListName());
                setPlaylistButton(playlistbtn, playList);
                gbc.gridx = 0;
                gbc.gridy = 4 + numberOfPlaylists;
                listsPnl.add(playlistbtn, gbc);
                numberOfPlaylists++;
            }
        }
        gbc2.gridx = 0;
        gbc2.gridy = 4 + numberOfPlaylists;
        gbc2.ipady = 0;       //reset to default
        gbc2.weighty = 1.0;   //request any extra vertical space
        gbc2.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbc2.insets = new Insets(10, 0, 0, 0);  //top padding
        listsPnl.add(addNewPlaylist, gbc2);
    }

    private void setColor(){
        songsbutton.setBackground(Color.white);
        albumebutton.setBackground(Color.white);
        playlistlbl.setBackground(Color.WHITE);
        addNewMusic.setBackground(Color.white);
        addNewPlaylist.setBackground(Color.white);
        createPlaylistPanel.setBackground(Color.WHITE);
        finishedCooshingBtn.setBackground(Color.WHITE);
    }

    private void setBorder(){
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(greenLIne);
        addNewMusic.setBorder(greenLIne);
        songsbutton.setBorder(greenLIne);
        albumebutton.setBorder(greenLIne);
        playlistlbl.setBorder(greenLIne);
        addNewPlaylist.setBorder(greenLIne);
        createPlaylistPanel.setBorder(greenLIne);
        finishedCooshingBtn.setBorder(greenLIne);
    }

    private void setSize(){
        addNewMusic.setPreferredSize(new Dimension(200 , 100));
        songsbutton.setPreferredSize(new Dimension(200, 100));
        albumebutton.setPreferredSize(new Dimension(200 , 100));
        playlistlbl.setPreferredSize(new Dimension(200 , 50));
        addNewPlaylist.setPreferredSize(new Dimension(200 , 100));
        createPlaylistPanel.setPreferredSize(new Dimension(200 , 100));
        finishedCooshingBtn.setPreferredSize(new Dimension(200 , 100));
    }

    private void addActionListeners(){
        addNewMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.addSong();
                } catch (Exception e1) {
                }
            }
        });
        songsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplaySongs(displayListsControl.getSongs());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        albumebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplayAlbums();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addNewPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newPlaylistFrame = new JFrame("create new playlist");
                newPlaylistFrame.setSize(200 , 200);
                newPlaylistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newPlaylistFrame.setLayout(new GridLayout(2 , 1));
                JTextField textField = new JTextField();
                textField.setEditable(true);
                textField.setSize(200 , 100);
                textField.setBackground(Color.WHITE);
                JButton createNewPlaylistbtn = new JButton("create !");
                createNewPlaylistbtn.setSize(200 , 100);
                createNewPlaylistbtn.setBackground(Color.WHITE);
                newPlaylistFrame.add(textField);
                newPlaylistFrame.add(createNewPlaylistbtn);
                newPlaylistFrame.setVisible(true);
                createNewPlaylistbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newPlaylistFrame.setVisible(false);
                        PlayList playList = displayListsControl.addPlaylist(textField.getText());
                        try {
                            displayListsControl.setSelectSongs(playMusicGUI, playList);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        listsPnl.remove(addNewPlaylist);
                        listsPnl.add(finishedCooshingBtn , gbc2);
                        mainGUI.revalidate();
                        mainGUI.repaint();
                    }
                });
            }
        });

        finishedCooshingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayListsControl.clean();
                listsPnl.remove(finishedCooshingBtn);
                gbc.gridx = 0;
                gbc.gridy = numberOfPlaylists + 4;
                System.out.println(numberOfPlaylists+4);
                JButton newPlaylist = new JButton(displayListsControl.getPlaylists().get(numberOfPlaylists).getPlayListName());
                setPlaylistButton(newPlaylist , displayListsControl.getPlaylists().get(numberOfPlaylists));
                System.out.println(displayListsControl.getPlaylists().get(numberOfPlaylists).getPlayListName());
                listsPnl.add(newPlaylist , gbc);
                System.out.println(3);
                numberOfPlaylists ++;
                gbc2.gridy = 4 + numberOfPlaylists;
                listsPnl.add(addNewPlaylist, gbc2);
                mainGUI.revalidate();
                mainGUI.repaint();
            }
        });
    }

    private void setPlaylistButton(JButton playlistbtn , PlayList playList){
        playlistbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayListsControl.setDisplayPlaylist(playList , playMusicGUI);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        if(playList.getPlaylistSituation().equals(PlaylistSituation.TEMPORARY)){
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem delet = new JMenuItem("delet");
            JMenuItem rename = new JMenuItem("rename");
            popupMenu.add(delet);
            popupMenu.add(rename);
            playlistbtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(e.getButton() == MouseEvent.BUTTON3){
                        popupMenu.show(playlistbtn , e.getX() , e.getY());
                    }
                }
            });
            delet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayListsControl.deletPlaylist(playList);
                    listsPnl.remove(playlistbtn);
                    mainGUI.revalidate();
                    mainGUI.repaint();
                }
            });
            rename.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame renameFrame = new JFrame("rename");
                    renameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    renameFrame.setLayout(new GridLayout(2 , 1));
                    renameFrame.setBackground(Color.WHITE);
                    JTextField renameTextField = new JTextField();
                    renameFrame.setSize(new Dimension(200 , 200));
                    renameTextField.setSize(new Dimension(200 , 100));
                    renameTextField.setBackground(Color.WHITE);
                    renameTextField.setEditable(true);
                    JButton done = new JButton("done");
                    done.setBackground(Color.WHITE);
                    done.setSize(200 , 100);
                    renameFrame.add(renameTextField);
                    renameFrame.add(done);
                    renameFrame.setVisible(true);
                    done.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            playList.setPlayListName(renameTextField.getText());
                            playlistbtn.setText(renameTextField.getText());
                            renameFrame.setVisible(false);
                            mainGUI.revalidate();
                            mainGUI.repaint();
                        }
                    });
                }
            });
        }
        playlistbtn.setBackground(Color.WHITE);
        Border greenLIne = BorderFactory.createLineBorder(Color.GREEN);
        playlistbtn.setBorder(greenLIne);
        playlistbtn.setPreferredSize(new Dimension(200 , 100));
        playlistsbtn.add(playlistbtn);
    }
}
