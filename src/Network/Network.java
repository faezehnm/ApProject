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
import java.util.Iterator;
import music.Song;
import org.omg.CORBA.Environment;

public class Network implements Runnable {
    private Socket client;
    private int port = 2000 ;
    private String serverName = "localhost";
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private OutputStream out;
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
        this.out = this.client.getOutputStream();

        int count;
        while((count = in.read(bytes)) > 0) {
            this.out.write(bytes, 0, count);
        }

        this.out.flush();
        this.out.close();
        in.close();
    }

    public void run() {
        try {
            this.inputStream = new ObjectInputStream(this.client.getInputStream());
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        while(true) {

            try {
                ForServer forServer = (ForServer)this.inputStream.readObject();
                switch(forServer.getType()) {
                case 0:
                case 8:
                case 9:
                case 11:
                case 13:
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
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }
                    break;

                case 12:
                    this.setLastSongOfFriend(forServer);
                    this.creatFriendPanel();
                }
            } catch (IOException var5) {
                var5.printStackTrace();
            } catch (ClassNotFoundException var6) {
                var6.printStackTrace();
            }

        }
    }

    private void AcceptLoginRequest(ForServer forServer) {
        try {
            jPotifyGUI = new JPotifyGUI(true);
            jPotifyGUI.setUser(forServer.getUser());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private void NOTAcceptLoginRequest() {
        logInGUI = new LogInGUI();
        this.warning = new Warning("incorrect pass");
    }

    private void AcceptSignUpRequest(ForServer forServer) {
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
        setFrindSharePlaylist(forServer);

        //new SendSharedPlaylist(jPotifyGUI.getUser());

        //new SendLastSong(jPotifyGUI.getUser());

    }

    private void request(ForServer forServer)
    {
        this.warning = new Warning(forServer.getUser().getPassword() + " request to follow you . if you accept press ok !");
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

    private void creatFriendPanel()
    {
        System.out.println("in netWork"+ jPotifyGUI.getUser().getFriends().size());
        jPotifyGUI.getFriendsActivityGUI().creatFirendPanel();
//        for(Song song : findFriend().getSharedPlayList().getSongs() ) {
//           // jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addSong(song);
//        }
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

    private void setFrindSharePlaylist(ForServer forServer) throws Exception {
        String path = null;
        for (int i = 0; i < forServer.getUser().getSharedPlaylist().getSongs().size(); i++) {
            String friendName = new String(forServer.getUser().getName());

            path = new String("src\\songs\\" + friendName + i + ".mp3");
            File f = new File(path);
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);

            fos.write(forServer.getUser().getSharedPlaylist().getSongs().get(i).getSongBytes());
            fos.flush();
            fos.close();

            addSongToFriendSharedPlaylist(path,friendName);

        }
        creatFriendPanel();
    }
    private void addSongToFriendSharedPlaylist(String path , String friendName) throws Exception {
        Song song = new Song(path);
        for( int i=0 ; i<jPotifyGUI.getUser().getFriends().size() ;i++ ){
            if( friendName.equals(jPotifyGUI.getUser().getFriends().get(i).getName())) {
                jPotifyGUI.getUser().getFriends().get(i).addSongToSharedPlayList(song);
                break;
            }
        }
    }
}
