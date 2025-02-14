
        package music;

import com.mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * This class represents a single song. Each song has an address , album , artist , name and artwork.
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @version 1.0
 * @since 2019
 */

public class Song extends Music implements Serializable {

    private String fileAddress;
    private String albumeName;
    private Albume albume;
    private String artist;
    private byte[] songBytes ;

    /**
     * ،This constructor creates a song and sets name , albume name , artist and artwork of the song.
     *
     * @param fileAddress The address of file in PC.
     * @throws Exception when the given address doesn"t exit.
     */

    public Song(String fileAddress) throws Exception {
        this.fileAddress = fileAddress;
        com.mpatric.mp3agic.Mp3File mp3File = new Mp3File(this.fileAddress);
        /*com.mpatric.mp3agic.ID3v1 id3v1 = mp3File.getId3v1Tag();
        try {
            artist = id3v1.getArtist();
            albumeName = id3v1.getAlbum();
            name = id3v1.getTitle();
        }catch (NullPointerException npe){
            artist = albumeName = name =  "____";
        }*/
        File file = new File(fileAddress);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(file.length() - 125);
        byte[] readenBytes = new byte[30];
        for (int i = 0; i < 30; i++) {
            readenBytes[i] = raf.readByte();
        }
        name = new String(readenBytes);
        for (int i = 0; i < 30; i++) {
            readenBytes[i] = raf.readByte();
        }
        artist = new String(readenBytes);
        for (int i = 0; i < 30; i++) {
            readenBytes[i] = raf.readByte();
        }
        albumeName = new String(readenBytes);
        raf.close();

        try {
            com.mpatric.mp3agic.ID3v2 id3v2 = mp3File.getId3v2Tag();
            artwork = id3v2.getAlbumImage();
        } catch (NullPointerException npe) {
            artwork = null;
        }

    }

    public String getFileAddress() {
        return fileAddress;
    }

    public String getAlbumeName() {
        return albumeName;
    }

    public Albume getAlbume() {
        return albume;
    }

    public String getArtist() {
        return artist;
    }

    public void setAlbume(Albume albume) {
        this.albume = albume;
    }

    /**
     * save song in byte array
     * @throws IOException
     */
    public void toByteArray() throws IOException {
        File file = new File(this.getFileAddress());
        long length = file.length();
        byte[] bytes = new byte[(int) length] ;
        InputStream ins = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int count ;
        while( (count = ins.read(bytes)) > 0){
            byteArrayOutputStream.write(bytes,0,count);
        }
        songBytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        ins.close();

    }

    public byte[] getSongBytes() {
        return songBytes;
    }
}