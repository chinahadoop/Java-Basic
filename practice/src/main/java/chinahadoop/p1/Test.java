package chinahadoop.p1;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private  List<String> lineList = new ArrayList<String>();

    public static void main(String args[]){
        List lineList = new ArrayList();
        Read r = new Read(lineList);
        r.readFile("test.txt");
        Write w = new Write(lineList);
        w.writeFile("test2.txt");
    }

}
