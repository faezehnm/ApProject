import javax.swing.*;

public class DisplayInformation extends JPanel {
    JLabel songNameLable = new JLabel();
    JLabel albumNameLable = new JLabel();
    JLabel artistNameLable = new JLabel();

    DefaultListModel<String> artWorkList = new DefaultListModel<>();

    public DisplayInformation(String songName , String albumName ,String artist){
        songNameLable.setText("song: "+ songName +"\n");
        albumNameLable.setText("album: "+ albumName +"\n");
        artistNameLable.setText("artist: "+ artist);
        artWorkList.addElement(songNameLable.getText());
        artWorkList.addElement(albumNameLable.getText());
        artWorkList.addElement(artistNameLable.getText());
        JList jList = new JList(artWorkList);
        add(jList);
    }
}
