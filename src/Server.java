import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {


    private ServerSocket server;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Server server = new Server();
        server.start();
        System.out.println("启动成功");
        server.receive();


    }

    //start
    public void start(){
        try {
            server = new ServerSocket(8000);
            this.receive();

           // this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receive(){
        try {
            Socket client = server.accept();
            StringBuilder sb = new StringBuilder();
            String msg = null;//接收客户端的请求信息

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while((msg = br.readLine()).length()>0){
                sb.append(msg);
                sb.append("\r\n");
                if (null == msg){
                    break;
                }
            }

            String requestInfo = sb.toString().trim();

            System.out.println(sb.toString());
            System.out.println("*********************************");
            System.out.println(requestInfo);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop(){

    }
};
