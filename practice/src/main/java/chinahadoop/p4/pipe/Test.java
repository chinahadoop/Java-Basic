package chinahadoop.p4.pipe;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by shen on 14-9-2.
 */
public class Test {

    public static void main(String args[]){
        Test pipe = new Test();
        pipe.process();
    }

    public void process(){
        PipedInputStream in = null;
        PipedOutputStream out = null;
        try{

            out = new PipedOutputStream();
            in = new PipedInputStream(out);
            new MyWriter(out).start();
            new MyReader(in).start();


        }catch (Exception e){e.printStackTrace();}
    }



}
