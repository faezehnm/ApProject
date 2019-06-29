package Network;

import FirendsActivity.Friend;
import Welcome.GoToJPotiy;
import Welcome.LogInGUI;
import Welcome.SignUpGUI;
import home.JPotifyGUI;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;

import music.Albume;
import music.PlayList;
import music.Song;
import org.omg.CORBA.Environment;

/**
 *  send & recive message betwen client and server
 *  @author faezeh naeimi
 *  @version 1.0
 *  @since 2019
 */
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
    public static Friend friend;

    /**
     * creat Network (when want to connect to server and send message )
     * @param forServer transfer messages between client and server
     * @throws IOException
     */
    public Network(ForServer forServer) throws IOException
    {
        this.client = new Socket(this.serverName, this.port);
        this.outStream = new ObjectOutputStream(this.client.getOutputStream());
        this.outStream.writeObject(forServer);
        this.outStream.flush();
    }

    /**
     * listen to server's messages
     */
    public void run() {
        try {
            this.inputStream = new ObjectInputStream(this.client.getInputStream());
        } catch (IOException var8) {

            var8.printStackTrace();
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
                    System.out.println("we are seting last song of friend");
                    this.setLastSongOfFriend(forServer);
                    updateFriendPanel(forServer);
                }
            } catch (IOException var5) {
                System.out.println("::::::in vsr 5");
                break;
                //var5.printStackTrace();
            } catch (ClassNotFoundException var6) {
                var6.printStackTrace();
            }

        }
    }

    /**
     * accept ligin requust from server( when password is correct )
     * @param forServer
     */
    private void AcceptLoginRequest(ForServer forServer)
    {
        try {
            File file = new File("src/" + forServer.getUser().getName() + ".bin");
            if (file.exists()){
                JPotifyGUI jPotifyGUI = new JPotifyGUI(false);
                FileInputStream fileIn = new FileInputStream(file.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                int lastSongExists = in.readInt();
                if(lastSongExists == 1){
                    Song s = (Song) in.readObject();
                    jPotifyGUI.getPlayMusicGUI().setSong(s,true);
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
            jPotifyGUI.getDisplayListsGUI().setUser(forServer.getUser());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    /**
     * reject ligin requust from server( when password is not correct )
     */
    private void NOTAcceptLoginRequest()
    {
        logInGUI = new LogInGUI();
        this.warning = new Warning("incorrect pass");
    }

    /**
     * accept SignUp requust from server( if there is no user whih my userName )
     * @param forServer
     */
    private void AcceptSignUpRequest(ForServer forServer)
    {
        try {
            jPotifyGUI = new JPotifyGUI(true);
            jPotifyGUI.setUser(forServer.getUser());
            jPotifyGUI.getDisplayListsGUI().setUser(forServer.getUser());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    /**
     * accept SignUp requust from server( if there is already a user whih my userName )
     */
    private void NOTAcceptSignUpRequest()
    {
        signUpGUI = new SignUpGUI();
        this.warning = new Warning("have this user already");
    }

    /**
     * when frind accept my request
     * @param forServer server mesaag
     * @throws Exception
     */
    private void freiendAccept(ForServer forServer) throws Exception
    {
        Friend friend = new Friend(forServer.getUser().getName());
        jPotifyGUI.getUser().addFriend(friend);
        setFrindSharePlaylist(forServer);


    }

    /**
     * when a user send me a request
     * @param forServer message from server
     */
    private void request(ForServer forServer)
    {
        this.warning = new Warning(forServer.getUser().getPassword() + " request to follow you . if you accept press ok !");
    }

    /**
     * when friend update her last song
     * @param forServer message from server
     */
    private void  setLastSongOfFriend(ForServer forServer)
    {
        for( int i=0 ; i<jPotifyGUI.getUser().getFriends().size() ; i++ ){
            if( forServer.getUser().getName().equals(jPotifyGUI.getUser().getFriends().get(i).getName())) {
                jPotifyGUI.getUser().getFriends().get(i).setLasSongIndex(forServer.getUser().getLasSongIndex());
                jPotifyGUI.getUser().getFriends().get(i).setLastSong();
                break;
            }
        }

    }

    /**
     * update friend panle when a user send her last song to all her firends
     * @param forServer message from user
     */
    private void updateFriendPanel(ForServer forServer)
    {
        for( int i=0 ; i<jPotifyGUI.getUser().getFriends().size() ; i++ ){
            if( forServer.getUser().getName().equals(jPotifyGUI.getUser().getFriends().get(i).getName())) {
                jPotifyGUI.getUser().getFriends().get(i).setLasSongIndex(forServer.getUser().getLasSongIndex());
                jPotifyGUI.getUser().getFriends().get(i).setLastSong();
                findFriendPanel(forServer.getUser().getName());
                break;
            }
        }
    }

    /**
     * find friend's panel
     * @param friendName message from user
     */
    private void findFriendPanel(String friendName)
    {

        for( int i=0 ; i<jPotifyGUI.getFriendsActivityGUI().getFriendsPanel().size() ; i++ ){
            if( jPotifyGUI.getFriendsActivityGUI().getFriendsPanel().get(i).getFriend().getName().equals(friendName) ){
                jPotifyGUI.getFriendsActivityGUI().getFriendsPanel().get(i).updateLastSongInformaion();

            }
        }
    }

    /**
     * creat friend's panel when accept my requst
     */
    private void creatFriendPanel()
    {
        System.out.println("in netWork"+ jPotifyGUI.getUser().getFriends().size());
        jPotifyGUI.getFriendsActivityGUI().creatFirendPanel();
//        for(Song song : findFriend().getSharedPlayList().getSongs() ) {
//           // jPotifyGUI.getDisplayListsGUI().getDisplayListsControl().addSong(song);
//        }
    }

    /**
     * find friend with her userName in my friends list
     * @return friend
     */
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

    /**
     * set friend's sharedPlaylist when accept my request
     * @param forServer message from server
     * @throws Exception
     */
    private void setFrindSharePlaylist(ForServer forServer) throws Exception
    {
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

    /**
     * add songs of friend to her sharedPlaylist
     * @param path adress of song which download from frind's sharedPlaylist
     * @param friendName frind name
     * @throws Exception
     */
    private void addSongToFriendSharedPlaylist(String path , String friendName) throws Exception
    {
        Song song = new Song(path);
        for( int i=0 ; i<jPotifyGUI.getUser().getFriends().size() ;i++ ){
            if( friendName.equals(jPotifyGUI.getUser().getFriends().get(i).getName())) {
                jPotifyGUI.getUser().getFriends().get(i).addSongToSharedPlayList(song);
                break;
            }
        }
    }


}
