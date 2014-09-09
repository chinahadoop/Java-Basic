package chinahadoop.p4.pipe;

import java.io.PipedOutputStream;
import java.io.PrintStream;

/**
 * Created by shen on 14-9-2.
 */
public class MyWriter extends Thread {
    private PipedOutputStream out = null;
    private String message[] = {"1","2"};

    public MyWriter(PipedOutputStream out){
        this.out = out;
    }

    public void run(){
        PrintStream  p  = new PrintStream(out);
        for(String mess:message){
            p.print(mess);
            p.flush();
        }
        p.close();
        p = null;
    }

}
