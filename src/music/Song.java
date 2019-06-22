package music;

import com.mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Song extends Music{

    private String fileAddress;
    private String albumeName;
    private Albume albume;
    private String artist;


    public Song(String fileAddress)throws Exception {
        this.fileAddress = fileAddress;
        com.mpatric.mp3agic.Mp3File mp3File = new Mp3File(this.fileAddress);
        com.mpatric.mp3agic.ID3v1 id3v1 = mp3File.getId3v1Tag();
        try {
            artist = id3v1.getArtist();
            albumeName = id3v1.getAlbum();
            name = id3v1.getTitle();
        }catch (NullPointerException npe){
            artist = albumeName = name =  "____";
        }
        try {
            com.mpatric.mp3agic.ID3v2 id3v2 = mp3File.getId3v2Tag();
            setArtwork(id3v2.getAlbumImage());
        }catch (NullPointerException npe){
            artwork = null;
        }
    }

    public String getFileAddress(){
        return fileAddress;
    }

    public String getMusicName(){
        return name;
    }

    public String getAlbumeName(){
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

    private void setArtwork (byte[] songImage){
        BufferedImage bufferedImage = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(songImage);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        artwork = bufferedImage;
    }

}
