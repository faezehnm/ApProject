//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Network;

import FirendsActivity.Friend;
import Welcome.GoToJPotiy;
import Welcome.LogInGUI;
import Welcome.SignUpGUI;
import home.JPotifyGUI;

import java.io.*;
import java.net.Socket;

import music.Albume;
import music.PlayList;
import music.Song;

public class Network implements Runnable {
    private Socket client;
    private int port = 2000;
    private String serverName = "localhost";
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private OutputStream out ;
    private Warning warning;
    public static JPotifyGUI jPotifyGUI;
    public static LogInGUI logInGUI;
    public static GoToJPotiy signUpGUI;
    public static boolean isOnNetworkType2 = false;
    public static Friend friend;

    public Network(ForServer forServer) throws IOException {
        this.client = new Socket(this.serverName, this.port);
        this.outStream = new ObjectOutputStream(this.client.getOutputStream());
        this.outStream.writeObject(forServer);
        this.outStream.flush();
    }

    public Network(InputStream in, byte[] bytes) throws IOException {
        this.client = new Socket(this.serverName, this.port);
        out = this.client.getOutputStream();

        int count;
        while((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }


        out.flush();
       // out.close();
        in.close();



    }

    public void run() {

        try {
            this.inputStream = new ObjectInputStream(this.client.getInputStream());
        } catch (IOException var8) {

            Thread currThread = Thread.currentThread();
            currThread.stop();
            System.out.println("in var8 :  "+jPotifyGUI.getUser().getName());

            ForServer forServer = new ForServer(13,jPotifyGUI.getUser());
            Network network = null;
            try {
                network = new Network(forServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new Thread(network).start();



            var8.printStackTrace();
        }

        while(true) {
            while(isOnNetworkType2) {
                if (isOnNetworkType2) {
                    isOnNetworkType2 = false;
                    try {
                        this.receiveFileFromServer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                ForServer forServer = (ForServer)this.inputStream.readObject();
                switch(forServer.getType()) {
                case 0:
                case 8:
                case 9:

                default:
                    break;
                case 1:
                    this.NOTAcceptSignUpRequest();
                    break;
                case 2:
                    this.AcceptSignUpRequest(forServer);
                    break;
                case 3:
                case 4:
                    this.NOTAcceptLoginRequest();
                    break;
                case 5:
                    this.AcceptLoginRequest(forServer);
                    break;
                case 6:
                    new Request(forServer, jPotifyGUI);
                    break;
                case 7:
                    System.out.println("friend accepted your request");
                    try {
                        this.freiendAccept(forServer);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                    break;
                case 10:
                    System.out.println("prepare");
                    isOnNetworkType2 = true;
                    friend = new Friend(forServer.getUser().getName());
                    break;

                case 11:
                /*
                send last song index
                 */
                break;

                case 12:
                    setLastSongOfFriend(forServer);
                    creatFriendPanel();
                    break;

                case 13:

                }

            } catch (IOException var6) {
                Thread currThread = Thread.currentThread();
                currThread.stop();
                System.out.println("in var6 :" + jPotifyGUI.getUser().getName());
                ForServer forServer = new ForServer(13,jPotifyGUI.getUser());
                Network network = null;
                try {
                    network = new Network(forServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new Thread(network).start();

                var6.printStackTrace();
            } catch (ClassNotFoundException var7) {
                var7.printStackTrace();
            }
        }
    }

    private void AcceptLoginRequest(ForServer forServer)
    {
        try {
            File file = new File("src/jpotify.bin");
            if (file.exists()){
                JPotifyGUI jPotifyGUI = new JPotifyGUI(false);
                FileInputStream fileIn = new FileInputStream(file.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                int lastSongExists = in.readInt();
                if(lastSongExists == 1){
                    Song s = (Song) in.readObject();
                    jPotifyGUI.getPlayMusicGUI().setSong(s);
                }
                int numberOfSongs = in.readInt();
                for(int i = 0 ; i < numberOfSongs ; i++){
                    Song s = (Song) in.readObject();
                    jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addSong(s);
                }
                int numberOfAlbumes = in.readInt();
                for(int i = 0 ; i < numberOfAlbumes ; i++){
                    Albume a = (Albume) in.readObject();
                    jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addAlbum(a);
                }
                int numberOfPlaylists = in.readInt();
                for(int i = 0 ; i < numberOfPlaylists ; i++){
                    PlayList p = (PlayList) in.readObject();
                    jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addPlaylist(p);
                }
                in.close();
            }
            else {
                JPotifyGUI jPotifyGUI = new JPotifyGUI(true);
            }
            jPotifyGUI.setUser(forServer.getUser());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private void NOTAcceptLoginRequest()
    {
        logInGUI = new LogInGUI();
        this.warning = new Warning("incorrect pass");
    }

    private void AcceptSignUpRequest(ForServer forServer)
    {
        try {
            jPotifyGUI = new JPotifyGUI(true);
            jPotifyGUI.setUser(forServer.getUser());
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    private void NOTAcceptSignUpRequest()
    {
        signUpGUI = new SignUpGUI();
        this.warning = new Warning("have this user already");
    }

    private void freiendAccept(ForServer forServer) throws Exception
    {
        Friend friend = new Friend(forServer.getUser().getName());
        jPotifyGUI.getUser().addFriend(friend);
        //new SendSharedPlaylist(jPotifyGUI.getUser());

        //new SendLastSong(jPotifyGUI.getUser());

    }

    private void request(ForServer forServer)
    {
        this.warning = new Warning(forServer.getUser().getPassword() + " request to follow you . if you accept press ok !");
    }

    private void receiveFileFromServer() throws Exception
    {
        InputStream in = null;
        OutputStream out = null;
        in = this.client.getInputStream();


        int index = friend.getFileIndex();
        String path = new String("src\\songs\\"+friend.getName()+index+".mp3");
        friend.setFileIndex(index++);

        FileOutputStream fileOutputStream = new FileOutputStream(path);

        out = fileOutputStream;
        byte[] bytes = new byte[16384];

        int count;
        while((count = in.read(bytes)) > 0) {
           out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        addSongToFriendPlayList(path);
//
//        Thread currThread = Thread.currentThread();
//        currThread.stop();
//
//        System.out.println("in receive file from server :  "+jPotifyGUI.getUser().getName());
//
//        ForServer forServer = new ForServer(13,jPotifyGUI.getUser());
//        Network network = null;
//        try {
//            network = new Network(forServer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        new Thread(network).start();
//


    }

    private void  setLastSongOfFriend(ForServer forServer)
    {
        for( int i=0 ; i<jPotifyGUI.getUser().getFriends().size() ; i++ ){
            if( forServer.getUser().getName().equals(jPotifyGUI.getUser().getFriends().get(i).getName())) {
                jPotifyGUI.getUser().getFriends().get(i).setLasSongIndex(forServer.getUser().getLasSongIndex());
                jPotifyGUI.getUser().getFriends().get(i).setLastSong();
                break;
            }
        }
        //jPotifyGUI.getFriendsActivityGUI().creatFirendPanel();
    }

    private void addSongToFriendPlayList(String path) throws Exception
    {

        Song song = new Song(path);

        for( int i=0 ; i<jPotifyGUI.getUser().getFriends().size() ;i++ ){
            if( friend.getName().equals(jPotifyGUI.getUser().getFriends().get(i).getName())) {
                jPotifyGUI.getUser().getFriends().get(i).addSongToSharedPlayList(song);
                break;
            }
        }

    }

    private void creatFriendPanel()
    {
        jPotifyGUI.getFriendsActivityGUI().creatFirendPanel();
        for(Song song : findFriend().getSharedPlayList().getSongs() ) {
            jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addSong(song);
        }
    }

    private Friend findFriend()
    {
        Friend result = null ;
        for( int i=0 ; i< jPotifyGUI.getUser().getFriends().size() ; i++ ){
            if( friend.getName().equals(jPotifyGUI.getUser().getFriends().get(i).getName()) ){
                result = jPotifyGUI.getUser().getFriends().get(i) ;
                break;
            }
        }
        return result;
    }
}
