package socketpied.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

/**
 * Created by linpeng123l on 2016/7/3.
 * lp
 */
public class ServerAccept extends Thread {

    private Socket socket;
    private PipedOutputStream pipedOutputStream;

    public ServerAccept(Socket socket, PipedOutputStream pipedOutputStream) {
        this.socket = socket;
        this.pipedOutputStream = pipedOutputStream;
    }

    @Override
    public void run() {
        super.run();
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bs = new byte[10240000];
            int len = 0;
            while ((len = inputStream.read(bs)) != -1) {
                System.out.println("接收数据------"+len);
                pipedOutputStream.write(bs, 0, len);
            }
            pipedOutputStream.flush();
            pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
