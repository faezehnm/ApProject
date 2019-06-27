package Network;

import home.JPotifyGUI;

import java.io.*;

public class SendSharedPlaylist {

    public SendSharedPlaylist(User user) throws IOException
    {

        for( int i=0 ; i<user.getSharedPlaylist().getSongs().size() ; i++ ){
            File file = new File(user.getSharedPlaylist().getSongs().get(i).getFileAddress() );

            long length = file.length();
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);

            ForServer forServer = new ForServer(9,user);
            Network network1 = new Network(forServer);
            new Thread(network1).start();


            try {
                Network network2 = new Network(in,bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


//        user.setFile(new File("src/songs/Happier.mp3"));
//
//        long length = user.getFile().length();
//        byte[] bytes = new byte[16 * 1024];
//        InputStream in = new FileInputStream(user.getFile());
//        ForServer forServer = new ForServer(9,user);
//        Network network1 = new Network(forServer);
//        new Thread(network1).start();

//
//        try {
//            Network network2 = new Network(in,bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
