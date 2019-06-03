package other;

import com.mpatric.mp3agic.Mp3File;

public class Song {

    private String fileAddress;
    private String musicName;
    private String albumeName;
    private Albume albume;
    private String artist;
    private byte[] artwork;


    public Song(String fileAddress)throws Exception {
        this.fileAddress = fileAddress;
        com.mpatric.mp3agic.Mp3File mp3File = new Mp3File(this.fileAddress);
        com.mpatric.mp3agic.ID3v1 id3v1 = mp3File.getId3v1Tag();
        artist = id3v1.getArtist();
        albumeName = id3v1.getAlbum();
        musicName = id3v1.getTitle();
        com.mpatric.mp3agic.ID3v2 id3v2 = mp3File.getId3v2Tag();
        artwork = id3v2.getAlbumImage();
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

    public byte[] getArtwork(){
        return artwork;
    }

    public void setAlbume(Albume albume){
        this.albume = albume;
    }
}
