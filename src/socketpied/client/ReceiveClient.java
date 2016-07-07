package socketpied.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by linpeng123l on 2016/7/3.
 * lp
 */
public class ReceiveClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",10000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("receiveClient\r\n".getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/linpeng123l/Downloads/b.zip");
        InputStream inputStream = socket.getInputStream();
        byte[] bs = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bs)) != -1) {
            fileOutputStream.write(bs, 0, len);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        System.out.println("发送客户端结束");
    }

}
