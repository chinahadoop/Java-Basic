package chinahadoop.p3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IoClient {

    public void client(String address,int port){
        Socket socket = null;
        try {
            socket = new Socket(address, port);
            //封装过滤流
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            //写数据
            pw.println("hello world");
            //注意刷新缓冲区
            pw.flush();
            //输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = br.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{socket.close();}catch(Exception e){}
        }

    }

    public static void main(String[] args) throws Exception {
        IoClient uu = new IoClient();
        uu.client("127.0.0.1",9001);
    }

}
