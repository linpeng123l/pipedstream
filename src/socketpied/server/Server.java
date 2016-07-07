package socketpied.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by linpeng123l on 2016/7/3.
 * lp
 */
public class Server {

    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream(10240000);
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedInputStream.connect(pipedOutputStream);
        ServerSocket serverSocket = new ServerSocket(10000);
        while (true) {
            System.out.println("开始监听...");
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String type = bufferedReader.readLine();
            System.out.println("连接请求,客户端类型:"+type);
            if ("sendClient".equals(type)) {
                new ServerAccept(socket,pipedOutputStream).start();
            } else {
                new ServerSend(socket,pipedInputStream).start();
            }
        }
    }

}
