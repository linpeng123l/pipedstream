package socketpied.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

/**
 * Created by linpeng123l on 2016/7/3.
 * lp
 */
public class ServerSend extends Thread{

    private Socket socket;
    private PipedInputStream pipedInputStream;

    public ServerSend(Socket socket,PipedInputStream pipedInputStream) {
        this.socket = socket;
        this.pipedInputStream = pipedInputStream;
    }

    @Override
    public void run() {
        super.run();
        try {
            OutputStream outputStream = socket.getOutputStream();
            byte[] bs = new byte[10240000];
            int len = 0;
            while ((len = pipedInputStream.read(bs))!=-1){
                System.out.println("读取数据发送"+len);
                outputStream.write(bs,0,len);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}