package Network;

import home.JPotifyGUI;

import java.io.*;

public class SendSharedPlaylist {

    public SendSharedPlaylist(User user) throws IOException
    {
        /*
        for( int i=0 ; i<user.getSharedPlayList().size ; i++ ){
            File file = new File(user.getSharedPlaylist().getSongs(i).getFileAdress )
        }
            user.setFile-->file
         */
        user.setFile(new File("src/songs/Happier.mp3"));

        long length = user.getFile().length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(user.getFile());
        ForServer forServer = new ForServer(9,user);
        Network network1 = new Network(forServer);
        new Thread(network1).start();


        try {
            Network network2 = new Network(in,bytes);
//           Thread thread = new Thread(network2);
//           thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ForServer forServer1 = new ForServer(19,user);
//        Network network3 = new Network(forServer1);
//        new Thread(network3).start();



    }
}
