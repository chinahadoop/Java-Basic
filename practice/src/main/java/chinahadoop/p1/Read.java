package chinahadoop.p1;

import java.io.*;
import java.util.List;

public class Read {

    public List lineList;
    public Read(List lineList){
        this.lineList = lineList;
    }
    public void readFile(String readPath){
        BufferedReader br = null;
        try {
            FileInputStream fin = new FileInputStream(readPath);
            Reader r = new InputStreamReader(fin, "GBK");
            br = new BufferedReader(r);
            String line;
            while( (line = br.readLine()) != null ){
                lineList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (br != null) try{br.close();}catch(Exception e){}
        }
    }
}
