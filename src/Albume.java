import java.util.ArrayList;

public class Albume {

    private String albumeName;
    private ArrayList<Song> songs = new ArrayList<Song>();
    private byte[] artwork;

    public Albume(Song song , String albumeName){
        this.albumeName = albumeName;
        songs.add(song);
        artwork = song.getArtwork();
    }

    public String getAlbumeName(){
        return albumeName;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void addSong(Song song){
        if(! songs.contains(song)){
            songs.add(song);
        }
    }

    public void deletSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
        }
    }

    public byte[] getArtwork(){
        return artwork;
    }

    public void setArtwork(){
        artwork = songs.get(0).getArtwork();
    }
}
