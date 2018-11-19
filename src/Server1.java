import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class Server1 {



//也可以用这个
// String datetime = tempDate.format(new Date(System.currentTimeMillis()));


    public static void main(String[] args) throws IOException {

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new java.util.Date());

        System.out.println("服务器正在开启，请等候...");

//在服务器开一个端口号：

        ServerSocket serverSocket = new ServerSocket(8888);

// 服务器端获取客户端的请求

        Socket socket = serverSocket.accept();

// 开一个字节输入流；这里的文件类型和客户端的文件类型保持一致：可以是文件，视频，图片等

        File file = new File("./"+datetime+".png");

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
        socket.close();
    }


}
