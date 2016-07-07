package socketpied.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by linpeng123l on 2016/7/3.
 * lp
 */
public class SendClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",10000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("sendClient\r\n".getBytes());
        FileInputStream fileInputStream = new FileInputStream("/Users/linpeng123l/Downloads/a.zip");
        byte[] bs = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bs)) != -1) {
            outputStream.write(bs, 0, len);
        }
        outputStream.flush();
        outputStream.close();
        System.out.println("发送客户端结束");
    }

}
