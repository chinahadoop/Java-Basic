package chinahadoop.p4.pipe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by shen on 14-9-2.
 */
public class MyReader extends Thread {
    private PipedInputStream in = null;
    private String message[] = {"1","2"};

    public MyReader(PipedInputStream in){
        this.in = in;
    }

    public void run(){
        String line = null;
        boolean reading = true;
        DataInputStream d  = new DataInputStream(in);
        while(reading && null!=d){
            try{
                line = d.readLine();
                if(null != line){
                    System.out.println("Read:"+line);
                }else{
                    reading = false;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
