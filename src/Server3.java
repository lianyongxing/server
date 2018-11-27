import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class Server3 {



//也可以用这个
// String datetime = tempDate.format(new Date(System.currentTimeMillis()));

    public static final int PORT = 8888;//监听的端口号


    public static void main(String[] args) throws IOException {


        //  System.out.println("服务器正在开启，请等候...");
        System.out.println("服务器启动...");
        Server3 server = new Server3();
        server.init();

    }


    public void init(){

        try {
            ServerSocket serverSocket = new ServerSocket(PORT,10, InetAddress.getByName("127.0.0.1"));
            //serverSocket.bind(192.168.19.158);
            while (true) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                // 处理这次连接
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("服务器异常: " + e.getMessage());
        }
    }


    private class HandlerThread implements Runnable{

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private Socket socket;
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        public void run() {
            try {

                String datetime = tempDate.format(new java.util.Date());

                File file = new File("./recvfiles/"+datetime+".PNG");

//开一个文件输出流，在服务器读接收客户端文件的同时，写出到指定路径地址

                FileOutputStream fileOutputStream = new FileOutputStream(file);

//通过字节缓冲流来实现，

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

//获取一个字节输入流

                InputStream inputStream = socket.getInputStream();

// 开一个字节缓冲流

                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                int len;

                byte[] buffer = new byte[1024];

                while ((len = bufferedInputStream.read(buffer)) != -1) {
                    bufferedOutputStream.write(buffer, 0, len);
                }

                System.out.println("接收完成...");
                bufferedInputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();

            } catch (Exception e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }

    }


}
