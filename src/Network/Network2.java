package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Network2 {
    private Socket client;
    private int port = 3000;
    private String serverName = "localhost";
    private OutputStream out ;


    public Network2(InputStream in, byte[] bytes) throws IOException {
        this.client = new Socket(this.serverName, this.port);
        out = this.client.getOutputStream();

        int count;
        while((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        byte[] STOP = "STOPCONN".getBytes() ;
        while((count = in.read(STOP)) > 0) {
            out.write(STOP, 0, count);
        }


        out.flush();
        out.close();
        in.close() ;

    }
}
