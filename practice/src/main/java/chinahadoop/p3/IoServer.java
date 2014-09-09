package chinahadoop.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class IoServer {

    private void lowToUpper(Socket socket){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = br.readLine();
            line = line.toUpperCase();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(line);
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{socket.close();}catch(Exception e){}
        }
    }


    public void server(int port) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                socket = serverSocket.accept();
                lowToUpper(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        IoServer us = new IoServer();
        us.server(9001);
    }

}