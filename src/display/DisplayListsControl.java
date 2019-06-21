package display;

import music.Song;
import music.Albume;
import music.PlayList;
import music.Music;
import home.JPotifyGUI;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import playControl.PlayMusicGUI;

public class DisplayListsControl {
    private ArrayList<Song> songs = new ArrayList();
    private ArrayList<Albume> albumes = new ArrayList();
    private ArrayList<PlayList> playlists = new ArrayList();
    private JPotifyGUI mainGUI;
    private DisplaySongsGroup displaySongsGroup = null;

    public DisplayListsControl(JPotifyGUI mainGUI) throws Exception {
        Song s1 = new Song("src/songs/Mohammad Alizadeh - Khateret Takht [128].mp3");
        this.addSong(s1);
        Song s2 = new Song("src/songs/Mehdi Yarrahi - Sarma Nazdike (128).mp3");
        this.addSong(s2);
        Song s3 = new Song("src/songs/Mehdi Yarrahi – Mesle Mojasameh128 (UpMusic).mp3");
        this.addSong(s3);
        Song s4 = new Song("src/songs/Ashvan - Mano Daryab.mp3");
        this.addSong(s4);
        Song s5 = new Song("src/songs/Mohsen_Sharifian-Mahalleye_Khomooni-(WWW.IRMP3.IR).mp3");
        this.addSong(s5);
        Song s6 = new Song("src/songs/Ashvan - Daram Ashegh Misham (128).mp3");
        this.addSong(s6);
        Song s7 = new Song("src/songs/Happier.mp3");
        this.addSong(s7);
        Song s8 = new Song("src/songs/03 Dar Astaneye Piri [320].mp3");
        this.addSong(s8);
        Song s9 = new Song("src/songs/02 To Dar Masafate Barani [320].mp3");
        this.addSong(s9);
        Song s10 = new Song("src/songs/Ehaam - Bezan Baran (128).mp3");
        this.addSong(s10);
        Song s11 = new Song("src/songs/Ehaam - Haale Man (128).mp3");
        this.addSong(s11);
        PlayList p1 = new PlayList("p1");
        this.playlists.add(p1);
        PlayList p2 = new PlayList("p2");
        this.playlists.add(p2);
        PlayList p3 = new PlayList("p3");
        this.playlists.add(p3);
        PlayList p4 = new PlayList("p4");
        this.playlists.add(p4);
        PlayList p5 = new PlayList("p5");
        this.playlists.add(p5);
        this.mainGUI = mainGUI;
    }

    private void addSong(Song s) {
        this.songs.add(s);
        System.out.println(this.songs.size());
        boolean albumeExists = false;
        Iterator var3 = this.albumes.iterator();

        while(var3.hasNext()) {
            Albume a = (Albume)var3.next();
            if (a.getName().equals(s.getAlbumeName())) {
                a.addSong(s);
                albumeExists = true;
                break;
            }
        }

        if (!albumeExists) {
            Albume albume = new Albume(s, s.getAlbumeName());
            this.addAlbum(albume);
        }

    }

    private void addAlbum(Albume albume) {
        this.albumes.add(albume);
    }

    public PlayList addPlaylist(String playlistName) {
        PlayList playList = new PlayList(playlistName);
        this.playlists.add(playList);
        return playList;
    }

    public void addSongToPlaylist(Song song, PlayList playList) {
        Iterator var3 = this.playlists.iterator();

        while(var3.hasNext()) {
            PlayList p = (PlayList)var3.next();
            if (p.equals(playList)) {
                p.addSong(song);
                break;
            }
        }

    }

    public void removePlaylist(PlayList pl) {
        this.playlists.remove(pl);
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public ArrayList<Albume> getAlbumes() {
        return this.albumes;
    }

    public ArrayList<PlayList> getPlaylists() {
        return this.playlists;
    }

    public void setDisplaySongs(ArrayList<Song> songs, PlayMusicGUI playMusicGUI) throws Exception {
        DisplaySongs displaySongs = new DisplaySongs(songs, playMusicGUI, true, (PlayList)null);
        this.clean();
        this.displaySongsGroup = displaySongs;
        this.mainGUI.getContentPane().add(this.displaySongsGroup);
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    public void setDisplayAlbums(PlayMusicGUI playMusicGUI) throws Exception {
        DisplayAlbumes displayAlbumes = new DisplayAlbumes(this.albumes, this, playMusicGUI);
        this.clean();
        this.displaySongsGroup = displayAlbumes;
        this.mainGUI.getContentPane().add(this.displaySongsGroup);
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    public void setDisplayPlaylist(PlayList playlist, PlayMusicGUI playMusicGUI) throws Exception {
        DisplaySongs displaySongs = new DisplaySongs(playlist.getSongs(), playMusicGUI, true, (PlayList)null);
        this.clean();
        this.displaySongsGroup = displaySongs;
        this.mainGUI.getContentPane().add(this.displaySongsGroup);
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    public void addSong() throws Exception {
        ChooseMusicFrame chooseMusicFrame = new ChooseMusicFrame();
        File songFile = chooseMusicFrame.getNewSong();
        if (songFile != null) {
            boolean fileExists = false;
            Iterator var4 = this.songs.iterator();

            while(var4.hasNext()) {
                Song s = (Song)var4.next();
                if (s.getFileAddress().equals(songFile.getPath())) {
                    fileExists = true;
                    break;
                }
            }

            if (!fileExists) {
                Song newSong = new Song(songFile.getPath());
                this.addSong(newSong);
            }
        }

    }

    public void setSelectSongs(PlayMusicGUI playMusicGUI, PlayList playList) throws Exception {
        DisplaySongs displaySongs = new DisplaySongs(this.songs, playMusicGUI, false, playList);
        this.clean();
        this.displaySongsGroup = displaySongs;
        this.mainGUI.add(this.displaySongsGroup, "Center");
        this.mainGUI.revalidate();
        this.mainGUI.repaint();
    }

    public void clean() {
        if (this.displaySongsGroup != null) {
            this.mainGUI.remove(this.displaySongsGroup);
            this.mainGUI.revalidate();
            this.mainGUI.repaint();
            this.displaySongsGroup = null;
        }

    }
}
