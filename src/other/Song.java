package other;

import com.mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Song {

    private String fileAddress;
    private String musicName;
    private String albumeName;
    private Albume albume;
    private String artist;
    private byte[] artwork;
    private BufferedImage artwork2;


    public Song(String fileAddress)throws Exception {
        this.fileAddress = fileAddress;
        com.mpatric.mp3agic.Mp3File mp3File = new Mp3File(this.fileAddress);
        com.mpatric.mp3agic.ID3v1 id3v1 = mp3File.getId3v1Tag();
        try {
            artist = id3v1.getArtist();
        }catch (NullPointerException npe){
            artist = "____";
        }
        try {
            albumeName = id3v1.getAlbum();
        }catch (NullPointerException npe){
            albumeName = "____";
        }
        try {
            musicName = id3v1.getTitle();
        }catch (NullPointerException npe){
            musicName = "_____";
        }
        try {
            com.mpatric.mp3agic.ID3v2 id3v2 = mp3File.getId3v2Tag();
            artwork = id3v2.getAlbumImage();
            setArtwprk2();
        }catch (NullPointerException npe){
            artwork2 = null;
        }
    }

    public String getFileAddress(){
        return fileAddress;
    }

    public String getMusicName(){
        return musicName;
    }

    public String getAlbumnane(){
        return albumeName;
    }

    public Albume getAlbume(){
        return albume;
    }

    public String getArtist(){
        return artist;
    }

    public void setAlbume(Albume albume){
        this.albume = albume;
    }

    public void setArtwprk2 (){
        BufferedImage bufferedImage = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(artwork);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        artwork2 = bufferedImage;
    }

    public BufferedImage getArtwork2(){
        return artwork2;
    }
}
